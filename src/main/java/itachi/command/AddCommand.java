package itachi.command;

import itachi.Storage;
import itachi.TaskList;
import itachi.exception.ItachiException;
import itachi.task.Task;

/**
 * Adds a new task to the task list and stores the data
 */
public class AddCommand extends Command {
    protected Task task;

    /**
     * Constructor to initialise the command object with the task to be added
     *
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes Add task command to add the given task to the task list
     *
     * @param taskList is an object of TaskList that consists of task operations
     * @param storage  is an object of Storage that saves current list of tasks
     * @throws ItachiException if any error occurs within adding tasks or saving data
     */
    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) throws ItachiException {
        taskList.addTask(task);
        storage.saveData();
    }
}
