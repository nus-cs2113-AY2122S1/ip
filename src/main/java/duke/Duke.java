package duke;

import duke.command.Command;

/**
 * Contains the entry point of the program.
 * Controls overall flow of program.
 */
public class Duke {
    public static boolean isRunning = true;

    public static void main(String[] args) {
        UserInterface.showGreet();
        DataManager.load();

        while (isRunning) {
            Command userCommand = UserInterface.interpretUserInput();
            UserInterface.executeCommand(userCommand);
            DataManager.saveWithoutSuccessMessage();
        }

        DataManager.save();
    }
}
