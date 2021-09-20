package duke.command;

import duke.exception.DeleteListIndexError;
import duke.tasks.TaskList;
import duke.exception.DoneListIndexError;
import duke.exception.DukeException;

import static duke.database.Database.saveFile;

public class DoneCommand extends Command {
    private int index;

    /**
     * Constructor
     *
     * @param target index of TaskList to be deleted
     */
    public DoneCommand(int target) {
        index = target;
    }

    /**
     * Executes DoneCommand
     *
     * @param tasks TaskList
     * @return appropriate message to be sent to user
     * @throws DoneListIndexError
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            //valid index
            tasks.updateTask(index);
            returnString = "Nice! I've marked this task as done:\n"
                    + "[X] " + tasks.getTask(index).getDescription();
            saveFile(tasks);
            return returnString;
        } catch (Exception e) {
            throw new DoneListIndexError();
        }
    }
}
