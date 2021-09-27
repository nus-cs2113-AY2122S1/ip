package karlett.commands;

import karlett.storage.StorageFile;
import karlett.task.Task;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, TextUi ui, StorageFile storageFile) throws IOException {
        Task task = tasks.get(index - 1);
        storageFile.removeTaskInFile(tasks, index);
        tasks.remove(index);
        ui.printTaskDeletedMessage(tasks, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
