package unker.command;

import unker.task.storage.TasksFileException;
import unker.ui.UI;
import unker.task.Unker;

/**
 * Command to run to exit from the software.
 * 
 * Usage in UI: bye
 */
public class ByeCommand extends Command {

    protected ByeCommand() {
        super("bye");
    }

    @Override
    public void execute(UI ui, Unker unker, String data) {
        ui.printByeMessage();
        try {
            unker.saveData();
        } catch (TasksFileException e) {
            ui.printTaskFileErrorMessage(e);
            System.exit(0);
        }
        System.exit(0);
    }
}
