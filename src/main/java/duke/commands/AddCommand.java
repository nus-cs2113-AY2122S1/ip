package duke.commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        ui.showAddMessage(task,tasks);
        storage.store(tasks);
    }
}
