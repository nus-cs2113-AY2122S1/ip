package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.ui.Ui;

/**
 * Command to add a task.
 */
public class AddTaskCommand extends Command {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private final String command;
    private final String description;
    private final String timeDescription;

    /**
     * Constructs a AddTaskCommand with command and description.
     *
     * @param command     The command indicating the task type to be added.
     * @param description The description of the task.
     */
    public AddTaskCommand(String command, String description) {
        this.command = command;
        this.description = description;
        this.timeDescription = "";
    }

    /**
     * Constructs a AddTaskCommand with command and details. The details
     * array contains the description and timing information.
     *
     * @param command The command indicating the task type to be added.
     * @param details Array containing the description and timing information.
     */
    public AddTaskCommand(String command, String[] details) {
        this.command = command;
        this.description = details[0];
        this.timeDescription = details[1];
    }

    /**
     * Executes the AddTaskCommand by adding the
     * task to the ArrayList tasks.
     *
     * @param taskManager Used to add task.
     * @param ui          Used to print message.
     * @throws DukeException If command is invalid.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        Task addedTask;
        switch (command) {
        case COMMAND_TODO:
            addedTask = new Todo(description);
            break;
        case COMMAND_DEADLINE:
            addedTask = new Deadline(description, timeDescription);
            break;
        case COMMAND_EVENT:
            addedTask = new Event(description, timeDescription);
            break;
        default:
            throw new DukeException("Invalid Command!");
        }
        taskManager.addTask(addedTask);
        ui.printAddTask(addedTask, taskManager);
    }
}
