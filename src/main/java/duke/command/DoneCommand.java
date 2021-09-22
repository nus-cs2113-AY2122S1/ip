package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private final int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (taskNumber <= 0 || taskNumber > taskList.getSize()) {
            throw new DukeException(Message.ERROR_TASK_NUMBER);
        }

        Task task = taskList.getTask(taskNumber - 1);
        taskList.markTaskAsDone(taskNumber - 1);
        ui.printTaskMarkedAsDone(task, taskList.getSize());
        storage.saveData(taskList.serialize());
    }
}
