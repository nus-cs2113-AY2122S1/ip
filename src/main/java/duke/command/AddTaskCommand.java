package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.ui.Ui;

public class AddTaskCommand extends Command {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private final String command;
    private final String description;
    private final String timeDescription;

    public AddTaskCommand(String command, String description) {
        this.command = command;
        this.description = description;
        this.timeDescription = "";
    }

    public AddTaskCommand(String command, String[] details) {
        this.command = command;
        this.description = details[0];
        this.timeDescription = details[1];
    }

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
