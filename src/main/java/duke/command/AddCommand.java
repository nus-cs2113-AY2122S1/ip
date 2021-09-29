package duke.command;
import duke.DukeException;
import duke.task.Task;
import duke.storage.TaskList;

import java.io.IOException;

/**
 * Represents all add commands
 * This class implements the <code>Command</code> interface
 *
 */
public class AddCommand implements Command {
    private Task task;

    /**
     * Constructor method for <code>AddCommand</code>
     * @param t the task to add
     */
    public AddCommand(Task t) {
        this.task = t;
    }

    /**
     * Executes the command
     *
     * @return result message if success
     * @throws DukeException if the task cannot be added into the memory
     */
    public String run() throws DukeException {
        try {
            TaskList.getInstance().add(this.task);
            String resultMsg = "Gotcha. Do this while you're at it:\n"
                    + "\t\t" + task.toString() + '\n'
                    + "\tNow you have " + (TaskList.getInstance().getTaskListSize())
                    + " tasks in the list.";
            return resultMsg;
        } catch (IOException e) {
            throw new DukeException("Cannot write task to memory!");
        }
    }
}
