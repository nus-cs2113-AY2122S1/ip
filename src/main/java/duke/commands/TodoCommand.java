package duke.commands;

import duke.data.TaskList;
import duke.data.task.Todo;
import duke.storage.Storage;
import duke.ui.TextUi;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task to the task list. "
            + "Parameters: TASK"
            + "\n|| "
            + "Example: " + COMMAND_WORD
            + " Wash my dirty smelly clothes\n||";

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        createTodoTask(tasks);
        storage.appendTodoToFile(description);
        ui.showSuccessfulAdd(tasks);
    }

    private void createTodoTask(TaskList tasks) {
        tasks.addTask(new Todo(description));
    }
}