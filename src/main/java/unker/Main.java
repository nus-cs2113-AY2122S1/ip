package unker;

import unker.command.CommandMap;
import unker.command.InvalidCommandException;
import unker.task.Unker;
import unker.task.storage.TasksFileException;
import unker.ui.UI;

/**
 * Main entrypoint to the program.
 */
public class Main {
    
    private UI ui;
    private Unker unker;
    private CommandMap commandMap;

    public static void main(String[] args) {
        new Main().run();
    }
    
    private void run() {
        this.start();
        this.handleCommands();
    }
    
    private void start() {
        this.ui = UI.getUiInstance();
        // Get the task manager instance
        this.unker = Unker.getUnkerInstance();
        // Attempt to load data from file.
        try {
            this.unker.loadData();
        } catch (TasksFileException e) {
            this.ui.printTaskFileErrorMessage(e);
        }
        this.commandMap = CommandMap.getCommandMapInstance();
        this.ui.printBanner();
    }
    
    private void handleCommands() {
        while (true) {
            String cmd = ui.getUserInput();
            try {
                commandMap.executeCommand(ui, unker, cmd);
            } catch (InvalidCommandException e) {
                ui.printInvalidCommandErrorMessage(e);
            } catch (TasksFileException e) {
                ui.printTaskFileErrorMessage(e);
            }
            ui.printRequestMoreCommandsMessage();
        }
    }
    
}
