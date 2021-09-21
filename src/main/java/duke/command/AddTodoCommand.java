package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Command to add a Todo.
 */
public class AddTodoCommand extends Command {
    private static final String COMMAND_TODO = "todo";
    private final String line;

    /**
     * Constructs an AddTodoCommand with the user input.
     *
     * @param description The user input.
     */
    public AddTodoCommand(String description) {
        this.line = description;
    }

    /**
     * Executes the AddTodoCommand by adding a Todo task to the
     * ArrayList of tasks.
     *
     * @param taskManager Used to add the Todo task.
     * @param ui          Used to print message.
     * @throws DukeException If the command description is empty.
     */
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        String rawDescription = Parser.parseDescription(line, COMMAND_TODO);
        Task addedTask = new Todo(rawDescription);
        taskManager.addTask(addedTask);
        ui.printAddTask(addedTask, taskManager);
    }
}
