package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the Bye command. Helps to do all operations of the bye command such as to print the bye statement
 * and to save the data to a file.
 */

public class ByeCommand extends Command {

    public ByeCommand(String command) {
        super(command);
        isExit = true;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        storage.saveData(ui, tasks);
        ui.customPrint("Bye. Hope to see you again soon!");
    }
}
