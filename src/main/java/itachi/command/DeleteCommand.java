package itachi.command;

import itachi.Storage;
import itachi.TaskList;
import itachi.exception.ItachiException;

public class DeleteCommand extends Command {
    protected int indexOfDelete;

    public DeleteCommand(int indexOfDelete) {
        this.indexOfDelete = indexOfDelete;
    }

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws ItachiException {
        taskList.deleteTask(indexOfDelete);
        storage.saveData();
    }
}
