package duke.control;

import duke.control.command.*;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        TaskList list = new TaskList();
        Storage.setPath();
        Storage.loadDataFromFile(list);
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
            command.executeCommand(list, userInput);
            Ui.printResponseSeparator();
        }
    }
}
