package commands;

import parser.DateParser;
import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;
import java.util.Date;

public class WhatsOnCommand extends Command {
    public static final String commandSyntax = "Command Syntax for WhatsOn: whatson <date in dd/MM/YYYY>";
    String date;

    public WhatsOnCommand(String command, String date) {
        super(command);
        this.date = date;
    }

    @Override
    public String help() {
        return "Invalid syntax.\n" + commandSyntax;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        StringBuilder taskString = new StringBuilder();
        String parsedDate = DateParser.formatDate(date);

        if (parsedDate == null) {
            ui.customPrint(help());
        } else {
            ArrayList<Task> filteredTasks = new ArrayList<>();

            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    String taskDate = DateParser.dateToString(((Deadline) task).getDate());
                    if (taskDate.equals(parsedDate)) {
                        filteredTasks.add(task);
                    }
                } else if (task instanceof Event) {
                    String taskDate = DateParser.dateToString(((Event) task).getAt());
                    if (taskDate.equals(parsedDate)) {
                        filteredTasks.add(task);
                    }
                }
            }

            // Checks if tasks exists
            if (filteredTasks.size() == 0) {
                taskString.append(Task.noTasks);
            } else {
                taskString.append(Task.hereAreYourTasks);
            }
            for (int i = 0; i < filteredTasks.size(); i++) {
                Task currentTask = filteredTasks.get(i);
                taskString.append(i + 1).append(".").append(currentTask.toString()).append("\n");
            }
            ui.customPrint(taskString.toString());
        }
    }
}
