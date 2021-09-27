package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import duke.parser.Parser;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Adds a Deadline Task
 * If no arguments provided by user, then error message is printed.
 * Calls splitByDelimiter from Parser to process command and arguments.
 */
public class AddDeadlineCommand extends Command {
    String arguments;

    public AddDeadlineCommand(String command, String arguments) {
        super(command);
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (arguments.equals("")) {
            ui.displayEmptyDescriptionResponse();
        } else {
            String delimiter = "/by";
            try {
                String[] splitArguments = Parser.splitByDelimiter(delimiter, arguments);
                String description = splitArguments[0];
                String by = splitArguments[1];

                LocalDateTime dateTime = Parser.parseDateTime(by);
                Deadline newDeadline = new Deadline(description, dateTime);
                tasks.addTask(newDeadline);

                Storage.saveData(tasks);

                int taskListSize = tasks.sizeOfTaskList();
                ui.acknowledgeAddedTask(newDeadline, taskListSize);
            } catch (StringIndexOutOfBoundsException e) {
                ui.displayDelimiterErrorMessage();
            } catch (DateTimeParseException e) {
                ui.displayInvalidDateTimeFormatResponse();
            }
        }
    }
}