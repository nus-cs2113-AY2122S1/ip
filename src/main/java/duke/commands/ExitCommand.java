package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasks.TaskList;

/**
 * Exits Duke loop
 */
public class ExitCommand extends Command {
    public final static String COMMAND_WORD = "bye";

    /**
     * Does nothing
     *
     * @param tasks   the TaskList that contains all current tasks
     * @param storage the storage object responsible for reading from/writing to the data file
     * @param ui      the ui object responsible for acknowledging to user that the command has been executed
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
    }
}
