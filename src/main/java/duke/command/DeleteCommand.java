package duke.command;

import duke.DukeException;
import duke.Util.Storage;
import duke.Util.Ui;
import duke.Util.Util;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private static final String MESSAGE_DELETE_TASK_FAILED = "Fail to delete task.";

    private static final String MESSAGE_FORMAT_DELETE_USAGE = "Usage: %s <task number>";
    private static final String MESSAGE_FORMAT_TASK_DELETED = "Task deleted:\n  %s\nThere are %d tasks left in the list.";

    public DeleteCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!Util.isStringInteger(argument)) {
            throw new DukeException(String.format(MESSAGE_FORMAT_DELETE_USAGE, COMMAND_DELETE));
        }

        int taskNumber = Integer.parseInt(argument);
        if (!tasks.isValidTaskNumber(taskNumber)) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        }

        Task removedTask = tasks.removeTaskAt(taskNumber - 1);
        if (removedTask == null) {
            throw new DukeException(MESSAGE_DELETE_TASK_FAILED);
        }

        storage.write(tasks);
        ui.printMessage(String.format(MESSAGE_FORMAT_TASK_DELETED, removedTask, tasks.getSize()));
    }
}
