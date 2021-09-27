package commands;

import task.Task;
import tasklist.TaskList;

/**
 * A class for passing the message from the execution of the Command to the Ui.
 */
public class CommandResult{
    public final String message;
    public final Task task;
    public final TaskList tasks;
    public final PrintOptions type;

    /**
     * Creates a CommandResult that allows the list of tasks to be shown to the user
     *
     * @param message Message to the user
     * @param tasks The list of tasks
     * @param type The options for printing in Ui.showResult
     */
    public CommandResult(String message, TaskList tasks, PrintOptions type) {
        this.message = message;
        this.task = null;
        this.tasks = tasks;
        this.type = type;
    }

    /**
     * Creates a CommandResult that only needs to show a message to the user.
     *
     * @param message Message to the user
     * @param type The options for printing in Ui.showResult
     */
    public CommandResult(String message, PrintOptions type) {
        this.message = message;
        this.task = null;
        this.tasks = null;
        this.type = type;
    }

    /**
     * Creates a CommandResult that requires a deleted/added task to be shown to the user.
     *
     * @param message Message to the user
     * @param task The task shown to the user
     * @param type The options for printing in Ui.showResult
     */
    public CommandResult(String message, Task task, PrintOptions type) {
        this.message = message;
        this.task = task;
        this.tasks = null;
        this.type = type;
    }

}
