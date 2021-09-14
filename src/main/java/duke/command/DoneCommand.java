package duke.command;

import duke.Storage;
import duke.DukeException;
import duke.TaskList;

public class DoneCommand extends Command{
    protected int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) throws DukeException {
        taskList.markTaskAsDone(taskIndex);
        storage.saveData(taskList);
    }
}
