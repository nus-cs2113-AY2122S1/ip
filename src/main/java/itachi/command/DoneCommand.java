package itachi.command;

import itachi.Storage;
import itachi.TaskList;
import itachi.exception.ItachiException;

public class DoneCommand extends Command {
    protected int indexOfDone;

    public DoneCommand(int indexOfDone) {
        this.indexOfDone = indexOfDone;
    }

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws ItachiException {
        taskList.doneTask(indexOfDone);
        storage.saveData();
    }
}
