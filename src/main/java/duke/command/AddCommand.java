package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    private static final String MESSAGE_INVALID_TASK_TYPE = "Invalid task type.";

    private static final String MESSAGE_FORMAT_TASK_ADDED = "Got it. Task added:\n  %s\nThere are %d tasks in the list.";
    private static final String MESSAGE_FORMAT_TODO_USAGE = "Usage: %s <description>";
    private static final String MESSAGE_FORMAT_DEADLINE_USAGE = "Usage: %s <description> %s <date/time>";
    private static final String MESSAGE_FORMAT_EVENT_USAGE = "Usage: %s <description> %s <date/time>";

    private static final String TASK_DEADLINE_SPLITTER = "/by";
    private static final String TASK_EVENT_SPLITTER = "/at";

    private char taskType;

    public AddCommand(char taskType) {
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        String[] descriptionAndArg;
        switch (taskType) {
        case Task.TYPE_TODO:
            if (argument.isEmpty()) {
                throw new DukeException(String.format(MESSAGE_FORMAT_TODO_USAGE, COMMAND_TODO));
            }
            task = tasks.addTodoTask(argument);
            break;

        case Task.TYPE_DEADLINE:
            descriptionAndArg = getTaskDescriptionAndArg(argument, TASK_DEADLINE_SPLITTER);
            if (descriptionAndArg[0].isEmpty() || descriptionAndArg[1].isEmpty()) {
                throw new DukeException(String.format(MESSAGE_FORMAT_DEADLINE_USAGE, COMMAND_DEADLINE, TASK_DEADLINE_SPLITTER));
            }
            task = tasks.addDeadlineTask(descriptionAndArg[0], descriptionAndArg[1]);
            break;

        case Task.TYPE_EVENT:
            descriptionAndArg = getTaskDescriptionAndArg(argument, TASK_EVENT_SPLITTER);
            if (descriptionAndArg[0].isEmpty() || descriptionAndArg[1].isEmpty()) {
                throw new DukeException(String.format(MESSAGE_FORMAT_EVENT_USAGE, COMMAND_EVENT, TASK_EVENT_SPLITTER));
            }
            task = tasks.addEventTask(descriptionAndArg[0], descriptionAndArg[1]);
            break;

        default:
            throw new DukeException(MESSAGE_INVALID_TASK_TYPE);
        }

        storage.write(tasks);
        ui.printMessage(String.format(MESSAGE_FORMAT_TASK_ADDED, task, tasks.getSize()));
    }

    /**
     * Gets the task description and argument.
     *
     * @param argument The argument from getCommandAndArgument(<string>).
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
}
