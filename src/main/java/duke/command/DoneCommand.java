package duke.command;

import duke.DukeException;
import duke.storage.TaskList;

import java.io.IOException;

public class DoneCommand implements Command {
    private int taskDoneNumber;

    public DoneCommand(int taskDoneNumber) {
        this.taskDoneNumber = taskDoneNumber;
    }

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
