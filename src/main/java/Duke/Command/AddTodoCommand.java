package Duke.Command;

import Duke.Ui.Ui;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Tasks.Todo;

public class AddTodoCommand extends Command {
    private final String description;

    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": add a todo task to the current list.\n"
            + " Parameters: DESCRIPTION\n"
            + " Example: " + COMMAND_WORD + " complete the assessment";

    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Execute the add todo command by adding a new todo task to TaskList
     *
     * @param tasks TaskList the command to be executed on
     * @param ui Ui used for execution
     * @param storage Storage which the command may make change on
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printAddTaskMessage(new Todo(this.description));
        tasks.addTodo(this.description);
        ui.printNumOfTasks(tasks);
        storage.save(tasks.getTasks());
    }
}
