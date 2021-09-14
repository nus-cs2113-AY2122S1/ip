package duke;

import duke.exception.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Messages.welcomeMessage();

        String userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            try {
                ArrayList<String> arguments = InputParser.parseInput(userInput);
                InputParser.handleInput(arguments);
            } catch (DukeCommandException e) {
                Messages.printDivider();
                System.out.println("Invalid command entered. Please try again");
                Messages.printDivider();
            }
            userInput = in.nextLine();
        }

        Messages.exitMessage();
    }
}
