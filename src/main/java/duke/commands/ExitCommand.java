package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Exits the program
 */
public class ExitCommand extends Command {
    /**
     * Saves the taskList, gives an error message if save failed
     * @param taskList the list to be saved
     * @param ui prints error message of save failed
     * @param storage saving taskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.save(taskList.getTaskList());
        } catch (IOException e) {
            ui.printError(e.toString());
        }
    }

    /**
     * Return true to exit the while loop and terminate program
     * @return true to exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
