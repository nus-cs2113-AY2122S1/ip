package duke.control;

import duke.control.command.ByeCommand;
import duke.control.command.Command;
import java.util.Scanner;

public class Duke {
    private static TaskList list;
    private static Storage storage;

    public Duke() {
        this.list = new TaskList();
        storage = new Storage();
        storage.loadDataFromFile(list);
        Ui.printWelcomeMessage();
    }

    public static void run() {
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = Ui.getUserResponse(in);
            Command command;
            try {
                command = Ui.processInput(userInput);
            } catch (InvalidInputFormatException e) {
                Ui.printInvalidInputMessage();
                continue;
            }
            if (command instanceof ByeCommand) {
                Ui.printExitMessage();
                Storage.saveData(list);
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
