package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Class constructor for DeleteCommand.
     *
     * @param taskIndex Specific index of task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the deletion of the specific task within the task list, specified by taskIndex.
     *
     * @param tasks   Task list that stores all the tasks
     * @param ui      User interface of Duke
     * @param storage Database of Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        tasks.deleteTask(taskIndex, ui);
    }
}
