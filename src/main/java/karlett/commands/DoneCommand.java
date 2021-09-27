package karlett.commands;

import karlett.storage.StorageFile;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, StorageFile storageFile) throws IOException {
        tasks.get(index - 1).markAsDone(index - 1);
        storageFile.updateTaskStatus(tasks, index - 1);
        ui.printMarkAsDoneMessage(tasks.get(index - 1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
