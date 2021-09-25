package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import duke.parser.Parser;
import duke.ui.Ui;

/**
 * Adds a Deadline Task
 * If no arguments provided by user, then error message is printed.
 * Calls splitByDelimiter to process command and arguments.
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
            String output = " ☹ OOPS!!! The description of a deadline cannot be empty.\n";
            ui.printOutput(output);
        } else {
            String delimiter = "/by";
            try {
                String[] splitArguments = Parser.splitByDelimiter(delimiter, arguments);
                String description = splitArguments[0];
                String by = splitArguments[1];

                Deadline newDeadline = new Deadline(description, by);
                tasks.addTask(newDeadline);
                int taskListSize = tasks.sizeOfTaskList();
                ui.acknowledgeAddedTask(newDeadline, taskListSize);
            } catch (StringIndexOutOfBoundsException e) {
                ui.displayDelimiterErrorMessage();
            }
        }
    }
}