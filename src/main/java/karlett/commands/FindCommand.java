package karlett.commands;

import karlett.storage.StorageFile;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {

    String keyWord = "";

    public FindCommand(String input) {
        keyWord = input;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, StorageFile storageFile) throws IOException {
        TaskList matchedTasks = new TaskList();
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            if (tasks.get(i).getDescription().contains(keyWord)) {
                matchedTasks.add(tasks.get(i));
            }
        }
        if (matchedTasks.getNumberOfTasks() == 0) {
            ui.printNoMatchedTaskFoundMessage(keyWord);
            return;
        }
        ui.printMatchedTasksMessage(matchedTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
