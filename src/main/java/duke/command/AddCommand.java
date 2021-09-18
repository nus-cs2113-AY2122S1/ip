package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class AddCommand extends Command {
    protected Task task;

    /**
     * Class add command constructor.
     *
     * @param task Task to be added to the list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Runs a command to add task to the list
     * and save changes to an external file.
     *
     * @param tasks   List that stores all the tasks.
     * @param storage Reference to the file where data is stored.
     */
    @Override
    public void runCommand(TaskList tasks, Storage storage) {
        tasks.addTask(task, true);
        storage.saveTask(tasks);
    }

}
