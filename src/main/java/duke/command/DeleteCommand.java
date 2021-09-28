package duke.command;

import duke.exception.DukeException;
import duke.util.TaskList;

/**
 * Represents the command to delete an existing Task from the task list. Contains index, the index of the task to be
 * deleted.
 */
public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the specified index from the given list of Tasks.
     * @param tl The list of tasks that the task is deleted from.
     */
    @Override
    public void execute(TaskList tl) {
        tl.deleteTask(index);
    }
}
