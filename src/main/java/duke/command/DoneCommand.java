package duke.command;

import duke.DukeException;
import duke.TaskManager;

public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Class done command constructor.
     *
     * @param taskNumber ID of task stored in list.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Runs a command to mark task as completed.
     *
     * @throws DukeException If an invalid task number is provided.
     */
    @Override
    public void runCommand() throws DukeException {
        TaskManager.markAsCompleted(taskNumber, true);
    }

    /**
     * Runs a command to mark task as completed when added from file.
     *
     * @throws DukeException If an invalid task number is read from file.
     */
    @Override
    public void runTaskDoneFromFile() throws DukeException {
        TaskManager.markAsCompleted(taskNumber, false);
    }
}
