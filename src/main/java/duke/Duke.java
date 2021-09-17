package duke;

import duke.exception.DukeCommandException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        DataFile.load();
        Messages.welcomeMessage();
        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            try {
                ArrayList<String> arguments = InputParser.parseInput(userInput.trim());
                InputParser.handleInput(arguments);
            } catch (DukeCommandException e) {
                Messages.printDivider();
                System.out.println("Invalid command entered. Please try again");
                System.out.println("For information on how to use me try using the help command!");
                Messages.printDivider();
            }
            userInput = in.nextLine();
        }

        Messages.exitMessage();
    }
}
