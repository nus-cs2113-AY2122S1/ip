package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{
    private int taskIndex;

    public DeleteCommand(String command, int taskIndex) {
        super(command);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task deletedTask = tasks.deleteTask(taskIndex);
        ui.showDeleteMessage(deletedTask, tasks);
        storage.store(tasks);
    }
}
