package duke.control;

import duke.control.command.ByeCommand;
import duke.control.command.Command;
import java.util.Scanner;

/**
 * Main class for Duke application
 */
public class Duke {
    private static TaskList list;
    private static Storage storage;

    public Duke() {
        this.list = new TaskList();
        storage = new Storage();
        storage.loadDataFromFile(list);
        Ui.printWelcomeMessage();
    }

    /**
     * Runs the main process of duke
     * Runs an infinite loop of receiving user input and processing it.
     * the "bye" command breaks the loop, ending the method.
     */
    public static void run() {
        Scanner in = new Scanner(System.in);
        String userInput;
        Command command;
        while (true) {
            userInput = Ui.getUserResponse(in);
            try {
                command = Ui.processInput(userInput);
            } catch (InvalidInputFormatException e) {
                Ui.printInvalidInputMessage();
                continue;
            }
            if (command instanceof ByeCommand) {
                command.executeCommand(list, userInput, storage);
                break;
            }
            command.executeCommand(list, userInput, storage);
            Ui.printResponseSeparator();
        }
    }

    public static void main(String[] args) {
        new Duke();
        run();
    }
}
