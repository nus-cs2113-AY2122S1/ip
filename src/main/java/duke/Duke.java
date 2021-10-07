package duke;

import java.util.Scanner;

/**
 * Shows the main class of Duke.
 */
public class Duke {

    public static void main(String[] args) {
        UI.printWelcomeMessage();
        String lineInput;
        Storage.getFromFile();
        do {
            Scanner input = new Scanner(System.in);
            lineInput = input.nextLine();
            Parser.parseInput(lineInput);
            UI.printBreaker();
        } while (!lineInput.equals("bye"));
        UI.printByeMessage();
    }
}