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

    /**
     * Delete the task at a specific index from the task list and storage file.
     *
     * @param tasks a TaskList that is already stored
     * @param ui text user interface
     * @param storageFile file to which command can write to
     * @throws IOException input or output exception
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, StorageFile storageFile) throws IOException {
        try {
            Task task = tasks.get(index - 1);
            storageFile.removeTaskInFile(tasks, index);
            tasks.remove(index);
            ui.printTaskDeletedMessage(tasks, task);
        } catch (NumberFormatException ex) {
            ui.printDeleteFormatErrorMessage();
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            ui.printOutOfBoundErrorMessage(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
