package command;

import exception.AustinException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeleteTaskCommand extends Command {
    public static final String COMMAND_KEYWORD = "delete";
    private int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws
            AustinException, IOException, IndexOutOfBoundsException {
        Task removedTask = tasks.deleteTask(taskIndex);
        storage.updateFile(tasks);
        ui.acknowledgeDelete(removedTask);
    }
}
