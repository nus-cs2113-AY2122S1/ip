package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskList;

/**
 * Represents the command to add a new Task to the task list. Contains addedTask, the task to be added.
 */
public class AddCommand extends Command {
    Task addedTask;

    public AddCommand(Task addedTask) {
        this.addedTask = addedTask;
    }

    /**
     * Adds the stored task to the specified list of tasks.
     * @param tl The list of tasks that the task is added to.
     */
    @Override
    public void execute(TaskList tl) {
        tl.addNewTask(addedTask);
    }
}
