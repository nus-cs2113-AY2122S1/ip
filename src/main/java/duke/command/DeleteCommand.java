package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class DeleteCommand extends Command{
    private Integer taskIndex;

    public DeleteCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes a task from tasklist.
     *
     * @param tasks Tasklist object used to store tasks
     * @param ui Ui object used to print messages
     * @param storage Storage object used to manipulate data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        tasks.deleteTask(taskIndex);
        storage.deleteTask(taskIndex);
        ui.printDeleteTaskMessage(tasks);
    }
}
