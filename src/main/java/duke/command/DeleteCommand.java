package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public class DeleteCommand extends Command {
    protected int indexOfDelete;

    public DeleteCommand(int indexOfDelete) {
        this.indexOfDelete = indexOfDelete;
    }

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.deleteTask(indexOfDelete);
        storage.saveData();
    }
}
