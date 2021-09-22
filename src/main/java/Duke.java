import commands.Command;
import commands.CommandList;
import tasks.Task;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import errors.InvalidCommand;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helps to start the application, load the data from file and initialise all variables
 * used as well as to continuously prompt for input from the user until "BYE" is received.
 */

public class Duke {
    private Ui ui;
    private Storage storage;
    ArrayList<Task> tasks;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.loadData(ui);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Prompts input from user and processes it indefinitely until "BYE" is received.
     */
    private void run() {
        this.ui = new Ui();
        ui.printWelcomeMessage();
        // String to store user's input
        String userInput = "";
        Scanner in = new Scanner(System.in);

        // Loops till bye is received
        while (true) {
            // Reads user input
            userInput = in.nextLine();
            try {
                Command command = Parser.processCommand(userInput);
                command.execute(ui, tasks, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (InvalidCommand e) {
                // Invalid Command
                ui.printInvalidCommandMessage();
            }
        }
    }
}
