package duke.commands;

import duke.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "delete";

    private static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:\n" + "  %1$s\n"
            + "Now you have %2$s task(s) in the list";
    private static final String MESSAGE_NONEXISTENT_TASK_NUMBER = "That task number does not exist!";

    private final int index;

    /**
     * Initialises command and stores index.
     *
     * @param index Index of the task to be deleted (starting from 0).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            final Task task = tasks.getTask(index);
            tasks.removeTask(task);
            return String.format(MESSAGE_TASK_DELETED, task, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_NONEXISTENT_TASK_NUMBER);
        }
    }
}
