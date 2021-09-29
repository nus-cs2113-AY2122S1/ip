package Duke.commands;

import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import Duke.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, ui);
        storage.writeToFile(tasks);
    }
}
