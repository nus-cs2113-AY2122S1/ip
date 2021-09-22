package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    protected int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printDeletedTask();
        ui.printToUser("        ", "deleted: ", taskList.getTask(deleteIndex).toString());
        taskList.deleteTask(deleteIndex);
    }
}
