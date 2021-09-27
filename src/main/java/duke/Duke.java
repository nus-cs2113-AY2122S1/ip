package duke;

import duke.command.CommandManager;
import java.util.Scanner;

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

        Message.printEnd();
    }
}
