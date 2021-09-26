package commands;

import data.Storage;
import data.TaskList;
import ui.TextUI;

import static common.Message.MESSAGE_EXIT;

/**
 * Represents the /bye command
 * This command terminates the program
 * COMMAND_WORD represents exact string user should provide to invoke this command
 */
public class ByeCommand extends Command{
    public static final String COMMAND_WORD = "/bye";

    /**
     * Sole constructor, no arguments
     */
    public ByeCommand() {}

    /**
     * Prints exit message
     * Overrides method from parent class
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        ui.showMessage(MESSAGE_EXIT);
    }
}
