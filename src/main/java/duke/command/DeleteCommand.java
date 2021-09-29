package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.exception.InvalidTaskException;
import duke.ui.Ui;
import java.io.IOException;

public class DeleteCommand extends Command {

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = --taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.deleteTask(taskNumber);
            storage.saveData(taskList.getTaskList());
            ui.printDeleteTaskMessage(task, taskList.getTaskList().size());
        } catch (InvalidTaskException | IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
