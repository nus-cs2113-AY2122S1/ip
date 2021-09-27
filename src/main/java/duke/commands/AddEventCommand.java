package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Event;
import duke.parser.Parser;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Adds an Event Task
 * If no arguments provided by user, then error message is printed.
 * Calls splitByDelimiter from Parser to process command and arguments.
 */
public class AddEventCommand extends Command {
    String arguments;

    public AddEventCommand(String command, String arguments) {
        super(command);
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (arguments.equals("")) {
            ui.displayEmptyDescriptionResponse();
        } else {
            String delimiter = "/at";
            try {
                String[] splitArguments = Parser.splitByDelimiter(delimiter, arguments);
                String description = splitArguments[0];
                String at = splitArguments[1];

                LocalDateTime dateTime = Parser.parseDateTime(at);
                Event newEvent = new Event(description, dateTime);
                tasks.addTask(newEvent);
                Storage.saveData(tasks);
                int taskListSize = tasks.sizeOfTaskList();
                ui.acknowledgeAddedTask(newEvent, taskListSize);
            } catch (StringIndexOutOfBoundsException e) {
                ui.displayDelimiterErrorMessage();
            } catch (DateTimeParseException e) {
                ui.displayInvalidDateTimeFormatResponse();
            }
        }
    }
}