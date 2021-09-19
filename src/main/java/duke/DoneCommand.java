package duke;

import duke.exception.DoneListIndexError;
import duke.exception.DukeException;

import static duke.Database.saveFile;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int target) {
        index = target;
    }

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
