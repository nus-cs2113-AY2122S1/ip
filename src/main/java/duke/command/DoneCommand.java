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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTaskAsDone(taskIndex);
        storage.alterTaskDone(taskIndex);
        ui.printMarkTaskAsDoneMesage();
    }
}
