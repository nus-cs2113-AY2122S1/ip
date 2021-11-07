package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

/**
 * Represents an unrecognised command input by the user
 * that cannot be parsed by the program.
 */
public class UnknownCommand extends Command {
    String description;

    public UnknownCommand(String description) {
        this.description = description;
    }

    /**
     * Gives user an appropriate error for a command not found.
     *
     * @param tasksList List of tasks
     * @param storage Storage object to save data
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        UI.printUnknownCommandMessage();
    }

    /**
     * Returns a boolean to check whether to exit application or not.
     *
     * @return false To continue taking user commands
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
