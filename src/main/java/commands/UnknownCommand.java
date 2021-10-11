package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;
import static common.Error.ERROR_INVALID_COMMAND;

/**
 * Represents a command that does not fall under commands
 * supported by the program
 */
public class UnknownCommand extends Command {
    /**
     * Sole constructor, no arguments
     */
    public UnknownCommand() {}

    /**
     * Outputs unrecognised command error
     * Overrides method from parent class
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        ui.showMessage(ERROR_INVALID_COMMAND);
    }
}
