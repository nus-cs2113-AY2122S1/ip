package duke.command;

import duke.Storage;
import duke.TaskList;

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
     * Runs a command to mark tasks as completed.
     *
     * @param tasks   List that stores all the tasks.
     * @param storage Reference to the file where data is stored.
     */
    @Override
    public void runCommand(TaskList tasks, Storage storage) {
        tasks.markAsCompleted(taskNumber, true);
        storage.saveTask(tasks);
    }

}
