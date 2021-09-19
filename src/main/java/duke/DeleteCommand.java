package duke;

import duke.exception.DeleteListIndexError;
import duke.exception.DukeException;
import duke.tasks.Task;
import java.util.ArrayList;

import static duke.Database.saveFile;
import static duke.Logic.listIndex;

public class DeleteCommand extends  Command{
    private int index;

    public DeleteCommand(int target) {
        index = target;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            //valid index
            returnString = "Noted. I've removed this task:\n" + tasks.getTask(index) + "\n";
            tasks.deleteTask(index);
            listIndex--;
            returnString += "Now you have " + String.valueOf(listIndex) + " tasks in the list.";
            saveFile(tasks);
            return returnString;
        } catch (Exception e) {
            throw new DeleteListIndexError();
        }
    }

}
