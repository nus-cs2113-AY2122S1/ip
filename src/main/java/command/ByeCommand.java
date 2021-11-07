package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

import java.io.IOException;

/**
 * Represents bye command given by user to exit application.
 */
public class ByeCommand extends Command {

    /**
     * Saves the data and gives the user
     * an appropriate exit message.
     *
     * @param tasksList  List of Tasks
     * @param storage    the storage object to save the data
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        try {
            storage.saveData(tasksList.getTasks());
            UI.printByeMessage();
        } catch (IOException e) {
            UI.printFailToWriteMessage();
        }

    }

    /**
     * Returns a boolean to check whether to exit application or not.
     *
     * @return true This tells the application to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
