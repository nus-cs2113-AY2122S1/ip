package karlett.commands;

import karlett.storage.StorageFile;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public class FindCommand extends Command {

    String keyWord = "";

    public FindCommand(String input) {
        keyWord = input;
    }

    /**
     * Find any task with a description that contains the key word.
     *
     * @param tasks a TaskList that is already stored
     * @param ui text user interface
     * @param storageFile file to which command can write to
     * @throws IOException input or output exception
     */
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
