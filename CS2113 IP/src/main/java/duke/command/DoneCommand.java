package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private final int taskIndex;

    /**
     * Class constructor for DoneCommand.
     *
     * @param taskIndex Specific index of task to be deleted.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the marking of the specific task as completed within the task list, specified by taskIndex.
     *
     * @param tasks   Task list that stores all the tasks
     * @param ui      User interface of Duke
     * @param storage Database of Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        int zeroIndex = taskIndex - 1;
        Task specifiedTask = tasks.get(zeroIndex);
        specifiedTask.markAsDone();
        ui.handleDone(specifiedTask);
    }
}
