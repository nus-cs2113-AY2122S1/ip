package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param ui      User interface of duke.
     * @param storage Reference to the file where data is stored.
     */
    @Override
    public void runCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markAsCompleted(taskNumber, ui, true);
        storage.saveTask(tasks);
    }

}
