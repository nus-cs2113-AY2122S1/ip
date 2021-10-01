package Duke.Command;

import Duke.Storage.Storage;
import Duke.Ui.Ui;
import Duke.DukeException.DukeException;
import Duke.Tasks.TaskList;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": Delete the task identified by the index number used in the current list.\n"
            + " Parameters: INDEX\n"
            + " Example: " + COMMAND_WORD + " 1";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the delete command by deleting a task from TaskList
     *
     * @param tasks TaskList the command to be executed on
     * @param ui Ui used for execution
     * @param storage Storage which the command may make change on
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printDeleteMessage(tasks.deleteTask(this.index));
        ui.printNumOfTasks(tasks);
        storage.save(tasks.getTasks());
    }
}
