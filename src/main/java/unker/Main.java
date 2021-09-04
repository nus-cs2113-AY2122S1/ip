package unker;

import unker.command.CommandMap;
import unker.ui.UI;

/**
 * Main entrypoint to the program.
 */
public class Main {

    public static void main(String[] args) {
        UI ui = UI.getUiInstance();
        Unker unker = Unker.getUnkerInstance();
        CommandMap commandMap = CommandMap.getCommandMapInstance();

        ui.printBanner();
        
        // Keep listening for new commands
        while (true) {
            String cmd = ui.getUserInput();
            commandMap.executeCommand(ui, unker, cmd);
            ui.printRequestMoreCommandsMessage();
        }
    }
    
}
