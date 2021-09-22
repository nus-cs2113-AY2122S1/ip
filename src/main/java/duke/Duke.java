package duke;

import duke.data.Storage;
import duke.exception.DukeCommandException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Storage.load();
        Ui.printWelcomeMessage();
        String userInput = input.nextLine();
        while (!userInput.equals("bye")) {
            try {
                ArrayList<String> arguments = Parser.parseInput(userInput);
                Parser.handleInput(arguments);
            } catch (DukeCommandException e) {
               Ui.printInvalidCommandMessage();
            }
            userInput = input.nextLine();
        }

        Ui.printExitMessage();
    }
}
