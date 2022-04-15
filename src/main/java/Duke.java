import java.io.IOException;
import parser.command.Command;
import parser.Parser;
import storage.Storage;
import task.TaskManager;
import ui.Ui;

public class Duke {

    /**
     * Main method
     * @param args System arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Start the Duke program
     */
    public void run() {
        Ui ui = new Ui();
        TaskManager taskMgr = new TaskManager();
        Parser parser = new Parser();

        boolean isExit = false;
        Command command;

        Storage storage;
        try {
            storage = new Storage();
            taskMgr.loadTasks(storage.readTaskData());
        }catch(IOException e) {
            ui.printErrorMessage(e.getMessage());
            return;
        }

        ui.printGreeting();

        while(!isExit) {
            String userInput = ui.getUserInput();
            command = parser.parseCommand(userInput);
            command.execute(storage, taskMgr, ui);

            isExit = command.isFinalCommand();
        }
    }
}