package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class DoneCommand extends Command{
    private Integer taskIndex;

    public DoneCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks a task as done.
     *
     * @param tasks Tasklist object used to store tasks
     * @param ui Ui object used to print messages
     * @param storage Storage object used to manipulate data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTaskAsDone(taskIndex);
        storage.alterTaskDone(taskIndex);
        ui.printMarkTaskAsDoneMesage();
    }
}
