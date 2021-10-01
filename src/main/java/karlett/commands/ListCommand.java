package karlett.commands;

import karlett.storage.StorageFile;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, TextUi ui, StorageFile storageFile) throws IOException {
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
