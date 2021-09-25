package itachi.command;

import itachi.Storage;
import itachi.TaskList;
import itachi.exception.ItachiException;
import itachi.task.Task;

public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws ItachiException {
        taskList.addTask(task);
        storage.saveData();
    }
}
