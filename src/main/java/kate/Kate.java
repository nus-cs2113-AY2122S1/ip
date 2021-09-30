package kate;

import kate.command.Command;
import kate.exception.FileCorruptedException;
import kate.exception.InvalidCommandException;
import kate.parser.Parser;
import kate.storage.Storage;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

import java.io.IOException;

public class Kate {

    private TaskList tasks;
    private KateUI ui;
    private Storage storage;

    public Kate() {
        ui = new KateUI();
        storage = new Storage();
        tasks = new TaskList();
    }

    public static void main(String[] args) {
        new Kate().startKate();
    }

    /**
     * Retrieve and process user inputs depending on the commands
     */
    private void startKate() {

        try {
            storage.loadDataFile(ui, tasks);
        } catch (IOException | FileCorruptedException e) {
            return;
        }

        ui.printGreetMessage();

        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getInput();
            try {
                Command command = Parser.extractCommand(userInput);
                command.execute(ui, storage, tasks);
                isExit = command.getExitStatus();
            } catch (InvalidCommandException e) {
                ui.printInvalidCommandMessage();
            }
        }
    }
}
