package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.exception.InvalidTaskException;
import duke.ui.Ui;
import java.io.IOException;

public class DoneCommand extends Command {

    private final int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.completeTask(taskNumber);
            storage.saveData(tasks.getTaskList());
            ui.printCompleteTaskMessage(task);
        } catch (InvalidTaskException | IOException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

}
