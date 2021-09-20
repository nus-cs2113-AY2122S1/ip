package duke.command;
import duke.DukeException;
import duke.storage.TaskList;

public class ListCommand implements Command {
    public String run() throws DukeException {
        String resultMsg = "";
        int i;

        if (TaskList.getInstance().getTaskListSize() == 0) {
            resultMsg = "Nothing";
            return resultMsg;
        }

        for (i = 0; i < TaskList.getInstance().getTaskListSize() - 1; i++) {
            resultMsg += (i + 1) + "."
                    + TaskList.getInstance().getTaskInfo(i) + '\n' + '\t';
        }
        resultMsg = resultMsg + TaskList.getInstance().getTaskListSize() + "."
                + TaskList.getInstance().getTaskInfo(i);

        return resultMsg;
    }
}
