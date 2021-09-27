package karlett.commands;

import karlett.storage.StorageFile;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public abstract class Command {

    protected String[] argumentsInWords;
    public TextUi ui;
    public boolean isExit = false;

    public abstract void execute(TaskList tasks, TextUi ui, StorageFile storageFile) throws IOException;

    public abstract boolean isExit();
}
