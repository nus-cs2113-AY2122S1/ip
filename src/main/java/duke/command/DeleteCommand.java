package duke.command;
import duke.DukeException;
import duke.storage.TaskList;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand implements Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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
