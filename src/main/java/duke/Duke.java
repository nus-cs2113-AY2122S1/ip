package duke;

import duke.command.CommandManager;
import java.util.Scanner;

/**
 * Main class that is started on project run.
 * Prints welcome message and enters a loop
 * that passes user input to {@link duke.command.CommandManager}.
 * If {@link duke.command.CommandManager#handleCommand(String)} function returns false
 * then the program will stop running.
 */
public class Duke {

    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        if (!IoManager.initialiseSaveFile()) {
            return;
        }
        Message.printWelcome();

        String userInput;
        do {
            userInput = IN.nextLine();
        } while (CommandManager.handleCommand(userInput));

        IN.close();
        Message.printEnd();
    }

}
