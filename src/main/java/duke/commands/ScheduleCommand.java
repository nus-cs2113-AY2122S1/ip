package duke.commands;

import duke.exceptions.DateTimeFormatException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Display all Tasks on specific date
 */
public class ScheduleCommand extends Command {
    String arguments;

    public ScheduleCommand(String command, String arguments) {
        super(command);
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> scheduledTasks = new ArrayList<>();
        if (arguments.equals("")) {
            String output = " ☹ OOPS!!! Please specify a date: yyyy-MM-dd.\n";
            ui.printOutput(output);
        } else {
            try {
                LocalDate parsedDate = Parser.parseDate(arguments);
                String formattedDate = Parser.getFormattedDate(parsedDate);

                for (int i = 1; i < tasks.sizeOfTaskList() + 1; i++) {
                    Task task = tasks.getTaskAtIndex(i-1);
                    if (task instanceof Deadline) {
                        String deadlineDate = ((Deadline) task).getDateString();
                        if (formattedDate.equals(deadlineDate)) {
                            scheduledTasks.add(task);
                        }
                    } else if (task instanceof Event) {
                        String eventDate = ((Event) task).getDateString();
                        if (formattedDate.equals(eventDate)) {
                            scheduledTasks.add(task);
                        }
                    }
                }

                String output = " Here is your schedule on " + formattedDate + ":\n";
                for (int i = 1; i < scheduledTasks.size() + 1; i++) {
                    output = output + " " + i + "." + scheduledTasks.get(i - 1).toString() + "\n";
                }
                ui.printOutput(output);
            } catch (DateTimeFormatException e) {
                ui.displayInvalidDateFormatResponse();
            }

        }

    }
}
