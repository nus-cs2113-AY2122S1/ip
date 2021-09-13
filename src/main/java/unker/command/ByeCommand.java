package unker.command;

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
        if (!unker.saveData()) {
            ui.printSection("Unker cannot save your data this session!");
        }
        ui.printByeMessage();
        System.exit(0);
    }
}
