package duke.commands;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Represents a command that adds tasks.
 */
public abstract class AddTaskCommand extends Command {
    /** Message to be displayed upon successful adding of task. */
    protected static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:\n" + "  %1$s\n"
            + "Now you have %2$s task(s) in the list";

    /** Task to be added. */
    protected Task toAdd;

    @Override
    public String execute(TaskList tasks) {
        tasks.addTask(toAdd);
        return String.format(MESSAGE_TASK_ADDED, toAdd, tasks.getSize());
    }
}
