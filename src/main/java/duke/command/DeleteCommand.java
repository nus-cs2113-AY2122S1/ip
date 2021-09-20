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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        tasks.deleteTask(taskIndex);
        storage.deleteTask(taskIndex);
        ui.printDeleteTaskMessage(tasks);
    }
}
