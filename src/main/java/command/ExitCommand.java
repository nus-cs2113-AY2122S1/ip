package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Ends the Austin operations
 */
public class ExitCommand extends Command {
    public static final String COMMAND_KEYWORD = "bye";

    /**
     * Executes ExitCommand
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }
}
