package duke.command;

import duke.DukeException;
import duke.storage.TaskList;

import java.io.IOException;

/**
 * Represents a done command
 * This class implements the <code>Command</code> interface
 *
 */
public class DoneCommand implements Command {
    private int taskDoneNumber;

    /**
     * Constructor method for <code>DoneCommand</code>
     *
     * @param taskDoneNumber the task number in the list to mark done
     */
    public DoneCommand(int taskDoneNumber) {
        this.taskDoneNumber = taskDoneNumber;
    }

    /**
     * Executes the command
     *
     * @return result message if success
     * @throws DukeException if the task number does not exist in the list, or cannot set the task as done in memory
     */
    public String run() throws DukeException {
        String resultMsg;

        try {
            TaskList.getInstance().setDone(taskDoneNumber);
            resultMsg = "Good job. You may now enjoy the rest of "
                    + "your suffering:\n"
                    + "\t" + TaskList.getInstance().getTaskInfo(taskDoneNumber - 1);
            return resultMsg;
        } catch (IOException e) {
            throw new DukeException("Err I cannot set this as done in memory");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number not exist!");
        }
    }
}
