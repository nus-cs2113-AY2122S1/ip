package karlett.commands;

import karlett.storage.StorageFile;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, TextUi ui, StorageFile storageFile) throws IOException {
        TextUi.drawDivider();
        System.out.println("Bye~Bye~ヾ(￣▽￣) Hope to see you again soon meow.");
        TextUi.drawDivider();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
