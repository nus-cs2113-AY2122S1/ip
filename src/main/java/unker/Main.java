package unker;

import unker.command.CommandMap;
import unker.command.InvalidCommandException;
import unker.ui.UI;

/**
 * Main entrypoint to the program.
 */
public class Main {

    public static void main(String[] args) {
        UI ui = UI.getUiInstance();
        // Get the task manager instance
        Unker unker = Unker.getUnkerInstance();
        CommandMap commandMap = CommandMap.getCommandMapInstance();

        ui.printBanner();
        
        // Keep listening for new commands
        while (true) {
            String cmd = ui.getUserInput();
            try {
                commandMap.executeCommand(ui, unker, cmd);
            } catch (InvalidCommandException e) {
                ui.printInvalidCommandErrorMessage(e);
            }
            ui.printRequestMoreCommandsMessage();
        }
    }
    
}
