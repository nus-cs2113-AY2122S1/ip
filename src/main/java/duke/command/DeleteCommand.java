package duke.command;
import duke.DukeException;
import duke.storage.TaskList;
import duke.task.Task;

import java.io.IOException;

/**
 * Represents a delete command
 * This class implements the <code>Command</code> interface
 *
 */
public class DeleteCommand implements Command {
    private int taskNumber;

    /**
     * Constructor method for <code>DeleteCommand</code>
     *
     * @param taskNumber the task number from the list to delete
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command
     *
     * @return result message if success
     * @throws DukeException if the task number does not exist in the list, or cannot delete the task from memory
     */
    public String run() throws DukeException {
        try {
            Task removedTask = TaskList.getInstance().delete(taskNumber);
            String resultMsg = "Noted. I have removed this thing:\n "
                    + "\t" + removedTask.toString();
            return resultMsg;
        } catch (IOException e) {
            throw new DukeException("Cannot delete task from memory!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number not exist!");
        }
    }
}
