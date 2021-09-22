package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    protected int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markDone(doneIndex);
        ui.printFinishedTask();
        ui.printToUser("        ", taskList.getTask(doneIndex).toString());
    }
}
