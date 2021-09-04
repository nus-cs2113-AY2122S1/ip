package duke;

public class AddCommand extends Command {
    Task task = null;

    /**
     * Constructor for AddCommand, command to add task to taskManager
     *
     * @param task the Task to be added to taskManager
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Calls the addTask method in taskManager to add the specified task
     *
     * @param taskManager The taskManager that the task will be added to
     * @throws DukeException If the maximum number of tasks has been reached
     */
    @Override
    public void execute(TaskManager taskManager) throws DukeException {
        taskManager.addTask(task);
    }
}
