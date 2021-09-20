import Commands.Command;
import Commands.ExitCommand;
import Exceptions.InsufficientParametersException;
import Exceptions.UnknownCommandException;
import Parser.Parser;
import Storage.Storage;
import Tasks.TaskList;
import UI.UserInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Main class
 */
public class Bob {

    private static UserInterface userInterface;
    private static Storage storage;
    private static TaskList taskList;

    public Bob() {
    }

    /**
     * Initialises Duke and Loads the saved Task file, if any
     */
    public static void init() {
        userInterface = new UserInterface();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            userInterface.printFileNotFoundMessage(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Waits for user command input, detects and executes it until exit command is given
     */
    public static void runCommandLoopUntilExitCommand() {
        Command command = new Command();
        do {
            String userInput = userInterface.getUserInput();
            try {
                command = new Parser().parse(userInput);
                String commandOutput = command.execute(taskList);
                userInterface.showOutputToUser(commandOutput);
                Storage.save(taskList);
            } catch (InsufficientParametersException | UnknownCommandException | IndexOutOfBoundsException |
                    NumberFormatException | IOException | DateTimeParseException e) {
                userInterface.printErrorMessage(e.getMessage());
            }
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Stops the execution of the program
     */
    public static void exit() {
        userInterface.printGoodbye();
        System.exit(0);
    }

    /**
     * The Main Method
     */
    public static void main(String[] args) {
        init();
        runCommandLoopUntilExitCommand();
        exit();
    }
}
