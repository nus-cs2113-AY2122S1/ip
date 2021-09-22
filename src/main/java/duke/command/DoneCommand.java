package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public class DoneCommand extends Command {
    protected int indexOfDone;

    public DoneCommand(int indexOfDone) {
        this.indexOfDone = indexOfDone;
    }

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.doneTask(indexOfDone);
        storage.saveData();
    }
}
