package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.addTask(task);
        storage.saveData();
    }
}
