package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        boolean isLessThanOne = taskNumber <= 0;
        boolean isMoreThanMax = taskNumber > taskList.getSize();
        boolean isTaskNumberValid = !isLessThanOne && !isMoreThanMax;

        if (!isTaskNumberValid) {
            throw new DukeException(Message.ERROR_TASK_NUMBER);
        }

        Task task = taskList.getTask(taskNumber - 1);
        taskList.deleteTask(taskNumber - 1);
        ui.printTaskDeleted(task, taskList.getSize());
        storage.saveData(taskList.serialize());
    }
}
