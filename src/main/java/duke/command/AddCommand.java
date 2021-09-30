package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.Util;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    private static final String MESSAGE_INVALID_TASK_TYPE = "Invalid task type.";

    private static final String MESSAGE_FORMAT_TASK_ADDED = "Got it. Task added:\n  %s\nThere are %d tasks in the list.";
    private static final String MESSAGE_FORMAT_TODO_USAGE = "Usage: %s <description>";
    private static final String MESSAGE_FORMAT_DEADLINE_USAGE = "Usage: %s <description> %s <%s>";
    private static final String MESSAGE_FORMAT_EVENT_USAGE = "Usage: %s <description> %s <%s>";
    private static final String MESSAGE_FORMAT_INVALID_DATETIME_FORMAT = "Invalid datetime format. Use %s instead.";

    private static final String TASK_DEADLINE_SPLITTER = "/by";
    private static final String TASK_EVENT_SPLITTER = "/at";

    private final char taskType;

    /**
     * Constructor for AddCommand class.
     *
     * @param argument The command argument.
     * @param taskType The type of task to create. Refer to constants in Task class.
     */
    public AddCommand(String argument, char taskType) {
        super(argument);
        this.taskType = taskType;
    }

    /**
     * Executes the Todo/Deadline/Event commands.
     *
     * @param tasks   The TaskList object.
     * @param ui      The Ui object.
     * @param storage The Storage object.
     * @throws DukeException if argument or taskType is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch (taskType) {
        case Task.TYPE_TODO:
            task = createTodoTask(argument);
            break;

        case Task.TYPE_DEADLINE:
            task = createDeadlineTask(argument);
            break;

        case Task.TYPE_EVENT:
            task = createEventTask(argument);
            break;

        default:
            throw new DukeException(MESSAGE_INVALID_TASK_TYPE);
        }

        tasks.addTask(task);
        storage.write(tasks);
        ui.printMessage(String.format(MESSAGE_FORMAT_TASK_ADDED, task, tasks.getSize()));
    }

    /**
     * Gets the task description and argument.
     *
     * @param argument    The argument from getCommandAndArgument(<string>).
     * @param splitString The string to split at.
     * @return String array: [0] - Description, [1] - Argument Value.
     */
    private String[] getTaskDescriptionAndArg(String argument, String splitString) {
        String[] argSplit = argument.split(splitString, 2);
        argSplit[0] = argSplit[0].trim();
        if (argSplit.length == 2) {
            argSplit[1] = argSplit[1].trim();
            return argSplit;
        }

        return new String[]{argSplit[0], ""};
    }

    /**
     * Creates a todo task.
     *
     * @param description The task description.
     * @return A Task object.
     * @throws DukeException If description is invalid.
     */
    private Task createTodoTask(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException(String.format(MESSAGE_FORMAT_TODO_USAGE, COMMAND_TODO));
        }

        return new Todo(description);
    }

    /**
     * Creates a deadline task.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @return A Task object.
     * @throws DukeException If argument is invalid.
     */
    private Task createDeadlineTask(String argument) throws DukeException {
        String[] descriptionAndArg = getTaskDescriptionAndArg(argument, TASK_DEADLINE_SPLITTER);
        if (descriptionAndArg[0].isEmpty() || descriptionAndArg[1].isEmpty()) {
            throw new DukeException(String.format(MESSAGE_FORMAT_DEADLINE_USAGE, COMMAND_DEADLINE, TASK_DEADLINE_SPLITTER, Task.DATETIME_FORMAT_INPUT));
        }

        LocalDateTime byDateTime = Util.getDateTimeFromString(descriptionAndArg[1], Task.DATETIME_FORMAT_INPUT);
        if (byDateTime == null) {
            throw new DukeException(String.format(MESSAGE_FORMAT_INVALID_DATETIME_FORMAT, Task.DATETIME_FORMAT_INPUT));
        }

        return new Deadline(descriptionAndArg[0], byDateTime);
    }

    /**
     * Creates an event task.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
     * @return A Task object.
     * @throws DukeException If argument is invalid.
     */
    private Task createEventTask(String argument) throws DukeException {
        String[] descriptionAndArg = getTaskDescriptionAndArg(argument, TASK_EVENT_SPLITTER);
        if (descriptionAndArg[0].isEmpty() || descriptionAndArg[1].isEmpty()) {
            throw new DukeException(String.format(MESSAGE_FORMAT_EVENT_USAGE, COMMAND_EVENT, TASK_EVENT_SPLITTER, Task.DATETIME_FORMAT_INPUT));
        }

        LocalDateTime atDateTime = Util.getDateTimeFromString(descriptionAndArg[1], Task.DATETIME_FORMAT_INPUT);
        if (atDateTime == null) {
            throw new DukeException(String.format(MESSAGE_FORMAT_INVALID_DATETIME_FORMAT, Task.DATETIME_FORMAT_INPUT));
        }

        return new Event(descriptionAndArg[0], atDateTime);
    }
}
