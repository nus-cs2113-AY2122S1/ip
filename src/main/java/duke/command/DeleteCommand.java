package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    protected int taskNumber;

    /**
     * Class delete command constructor.
     *
     * @param taskNumber ID of task stored in list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Runs a command to delete a task
     *
     * @param tasks   List that stores all the tasks.
     * @param ui      User interface of duke.
     * @param storage Reference to the file where data is stored.
     */
    @Override
    public void runCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(taskNumber, ui);
        storage.saveTask(tasks);
    }

}
