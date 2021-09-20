package duke.command;
import duke.DukeException;
import duke.task.Task;
import duke.storage.TaskList;

import java.io.IOException;

public class AddCommand implements Command {
    private Task task;

    public AddCommand(Task t) {
        this.task = t;
    }

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
