package duke.command;

import duke.exception.DukeException;
import duke.util.TaskList;

/**
 * Represents the command to mark an existing Task in the task list as done. Contains index, the index of the task to
 * be marked done.
 */
public class DoneCommand extends Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index from the given list of Tasks as done.
     * @param tl The list of tasks that the task is marked done.
     */
    @Override
    public void execute(TaskList tl) {
        try {
            tl.markTaskDone(index);
        } catch (DukeException e) {
            System.out.println("DukeException: " + e.getMessage());
        }
    }
}
