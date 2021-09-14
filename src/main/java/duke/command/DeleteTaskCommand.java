package duke.command;

import duke.Storage;
import duke.DukeException;
import duke.TaskList;

public class DeleteTaskCommand extends Command{
    protected int taskIndex;

    public DeleteTaskCommand(int taskIndex) { this.taskIndex = taskIndex; }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.deleteTask(taskIndex);
        storage.saveData(taskList);
    }
}
