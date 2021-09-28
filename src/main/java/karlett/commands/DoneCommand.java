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

    /**
     * Change the status of the task at a specific index
     * to "done" from the task list and storage file.
     *
     * @param tasks a TaskList that is already stored
     * @param ui text user interface
     * @param storageFile file to which command can write to
     * @throws IOException input or output exception
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, StorageFile storageFile) throws IOException {
        try {
            tasks.get(index - 1).markAsDone(index - 1);
            storageFile.updateTaskStatus(tasks, index - 1);
            ui.printMarkAsDoneMessage(tasks.get(index - 1));
        } catch (NumberFormatException ex) {
            ui.printDoneFormatErrorMessage();
        } catch (IndexOutOfBoundsException | NullPointerException en) {
            ui.printOutOfBoundErrorMessage(tasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
