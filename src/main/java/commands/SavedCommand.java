package commands;

import processors.Storage;
import processors.UI;
import processors.TaskList;

import java.io.IOException;

public class SavedCommand extends Command {
    public UI ui = new UI();
    public Storage storage = new Storage();

    /**
     * Function attempts to save the task list by calling the saveTasks method
     * in storage
     * @param taskList the list of tasks to be saved
     * @throws IOException when an error has occurred while saving
     */
    public void execute(TaskList taskList) throws IOException {
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            throw new IOException(e);
        }
        ui.printSavedMessage();
    }
}
