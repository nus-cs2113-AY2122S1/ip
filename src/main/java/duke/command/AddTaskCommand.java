package duke.command;

import duke.Storage;
import duke.DukeException;
import duke.TaskList;
import duke.task.Task;

public class AddTaskCommand extends Command{
    protected Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.addTasks(task, true);
        storage.saveData(taskList);
    }
}
