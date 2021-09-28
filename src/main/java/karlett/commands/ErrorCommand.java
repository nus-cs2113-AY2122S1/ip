package karlett.commands;

import karlett.storage.StorageFile;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public class ErrorCommand extends Command {

    /* An error command does not execute anything. */
    @Override
    public void execute(TaskList tasks, TextUi ui, StorageFile storageFile) throws IOException {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
