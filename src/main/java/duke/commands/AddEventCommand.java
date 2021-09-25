package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Event;
import duke.parser.Parser;
import duke.ui.Ui;

/**
 * Adds an Event Task
 * If no arguments provided by user, then error message is printed.
 * Calls splitByDelimiter to process command and arguments.
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
            String output = " ☹ OOPS!!! The description of an event cannot be empty.\n";
            ui.printOutput(output);
        } else {
            String delimiter = "/at";
            try {
                String[] splitArguments = Parser.splitByDelimiter(delimiter, arguments);
                String description = splitArguments[0];
                String at = splitArguments[1];

                Event newEvent = new Event(description, at);
                tasks.addTask(newEvent);
                int taskListSize = tasks.sizeOfTaskList();
                ui.acknowledgeAddedTask(newEvent, taskListSize);
            } catch (StringIndexOutOfBoundsException e) {
                ui.displayDelimiterErrorMessage();
            }
        }
    }
}