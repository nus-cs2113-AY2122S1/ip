package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;
import duke.util.Util;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private static final String MESSAGE_FORMAT_DONE_USAGE = "Usage: %s <task number>";

    private static final String MESSAGE_FORMAT_TASK_ALREADY_MARKED = "Task #%d is already marked as done.";
    private static final String MESSAGE_FORMAT_TASK_MARKED = "Task marked as done:\n  %s";

    public DoneCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!Util.isStringInteger(argument)) {
            throw new DukeException(String.format(MESSAGE_FORMAT_DONE_USAGE, COMMAND_DONE));
        }

        int taskNumber = Integer.parseInt(argument);
        if (!tasks.isValidTaskNumber(taskNumber)) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        }

        // Get task object associated to the task index from list
        Task task = tasks.getTaskAt(taskNumber - 1);

        if (task.isDone()) {
            throw new DukeException(String.format(MESSAGE_FORMAT_TASK_ALREADY_MARKED, taskNumber));
        }

        task.setAsDone();

        storage.write(tasks);
        ui.printMessage(String.format(MESSAGE_FORMAT_TASK_MARKED, task));
    }
}
