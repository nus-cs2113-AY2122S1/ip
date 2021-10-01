package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import duke.exception.ErrorCreateFileException;
import java.io.IOException;

public class ByeCommand extends Command {
    /**
     * Exit the program
     *
     * @param list The list of all tasks
     * @param doneList The list of all tasks which have been finished
     * @param ui The ui that is used
     * @throws ErrorCreateFileException If there are errors
     *                                  when creating files, exception occurs
     */
    @Override
    public void executeCommand(TaskList list, TaskDoneList doneList, Ui ui) throws ErrorCreateFileException {
        Storage storage = new Storage("dukeList.txt", "dukeDoneList.txt");
        try {
            storage.createListFile(list);
            storage.createDoneListFile(doneList);
        } catch (IOException e) {
            throw new ErrorCreateFileException();
        }
        ui.showTerminateScreen(ui);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
