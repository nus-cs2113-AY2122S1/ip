package duke.command;

import duke.tasks.TaskList;
import duke.exception.DeleteListIndexError;
import duke.exception.DukeException;

import static duke.database.Database.saveFile;
import static duke.logic.Logic.listIndex;

public class DeleteCommand extends  Command{
    private int index;

    /**
     * Constructor
     *
     * @param target index of TaskList to be deleted
     */
    public DeleteCommand(int target) {
        index = target;
    }

    /**
     * Executes DeleteCommand
     *
     * @param tasks TaskList
     * @return appropriate message to be sent to user
     * @throws DeleteListIndexError
     */
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
