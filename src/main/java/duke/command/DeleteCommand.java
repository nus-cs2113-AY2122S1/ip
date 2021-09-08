package duke.command;

import duke.DukeException;
import duke.TaskManager;

public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Class delete command constructor.
     *
     * @param taskNumber ID of task stored in list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Runs a command to delete task.
     *
     * @throws DukeException If an invalid task number is provided.
     */
    @Override
    public void runCommand() throws DukeException {
        TaskManager.deleteTask(taskNumber);
    }

}
