package Duke.commands;

import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import Duke.Ui;

public class AddCommand extends Command {
    private Task task;

    /**
     * Class constructor of AddCommand
     *
     * @param task Task to be added to tasks
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to tasks
     * Saves tasks to file
     *
     * @param tasks Tasklist for Task to be added to
     * @param ui User interface
     * @param storage File management interface
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task, ui);
        storage.writeToFile(tasks);
    }
}
