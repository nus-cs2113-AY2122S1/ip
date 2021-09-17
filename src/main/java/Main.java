import commands.Command;
import commands.CommandList;
import tasks.Task;
import tasks.TaskList;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import errors.InvalidCommand;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private Ui ui;
    private Storage storage;

    public Main(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList.tasks = storage.loadData();
    }

    public static void main(String[] args) {
        new Main("tasks.txt").run();
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
            Command command = new Parser().processCommand(userInput);
            try {
                if (command.getCommand().equals(CommandList.BYE)){
                    storage.saveData(TaskList.tasks);
                    ui.customPrint("Bye. Hope to see you again soon!");
                    break;
                }
                ui.customPrint(command.execute());
            } catch (InvalidCommand e) {
                // Invalid Command
                ui.printInvalidCommandMessage();
            }
        }
    }
}
