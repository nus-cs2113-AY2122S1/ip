package duke.commands;

import duke.data.TaskList;
import duke.data.task.Todo;
import duke.storage.Storage;
import duke.ui.TextUi;

/**
 * Adds a Todo task to the task list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task to the task list. "
            + "Parameters: TASK"
            + "\n|| "
            + "Example: " + COMMAND_WORD
            + " Wash my dirty smelly clothes\n||";

    private final String description;

    /**
     * Simple constructor with raw values.
     *
     * @param description a string that contains the task description
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Appends a Todo task to the TasKList and storage file.
     * Shows the user a message for successful creation.
     *
     * @param tasks a task list that contains all the tasks
     * @param ui accesses format and messages to show to the user
     * @param storage accesses a text file which stores the task list
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        createTodoTask(tasks);
        storage.appendTodoToFile(description);
        ui.showSuccessfulAdd(tasks);
    }

    private void createTodoTask(TaskList tasks) {
        tasks.addTask(new Todo(description));
    }
}