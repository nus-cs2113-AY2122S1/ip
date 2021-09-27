package duke;

import duke.command.CommandManager;
import java.util.Scanner;

/**
 * Main class that is started on project run.
 * Prints welcome message and enters a loop
 * that passes user input to <code>CommandManager</code> using a static Scanner <code>IN</code>.
 * if <code>handleCommand()</code> function returns false thenclose scanner
 * and print exit message.
 */
public class Duke {

    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        if(!IoManager.initialiseSaveFile()){
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
