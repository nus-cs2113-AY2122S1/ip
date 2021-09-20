import commands.Command;
import commands.ExitCommand;
import console.InputParser;
import error.Error;
import task.TaskManager;
import ui.Display;
import filemanager.Storage;

import java.io.IOException;
import java.util.Scanner;

/**
 * The starting point of Duke Program.
 * It starts the application with all the necessary components for interaction with user.
 */
public class Duke {

    private Storage storage;
    private TaskManager taskManager;
    private Scanner inputScanner;

    /** Initializes the Duke program with a greeting and load any saved task data from storage. */
    public Duke() {
        try {
            String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            Display.displayGreetings();
            storage = new Storage();
            taskManager = new TaskManager();
            inputScanner = new Scanner(System.in);
            storage.loadFileData(taskManager);
        } catch (IOException e) {
            Error.displayFileLoadError();
        }
    }

    /** Runs the program till the user terminates it. */
    public void run() {
        interact();
        Display.displayGoodbyes();
    }

    /**
     * Interacts with user by waiting for command line inputs.
     * It will continue to interact with the user till the program is terminated by a user command.
     * The command line inputs are parsed accordingly and the appropriate response would be displayed to the user.
     * The tasks saved in storage would be updated after every command.
     */
    public void interact() {
        Command command;
        do {
            String userInput = InputParser.getUserCommand(inputScanner);
            String[] commandComponents = InputParser.getCommandComponents(userInput);
            command = InputParser.parseCommand(commandComponents, taskManager);
            command.setCommandComponents(commandComponents);
            try {
                storage.updateFileData(taskManager.getAllTasks());
            } catch (IOException e) {
                Error.displayFileUpdateError();
            }
        } while (!command.executeCommand().equals(ExitCommand.COMMAND_WORD));
    }

    /** Starts the program. */
    public static void main(String[] args) {
        new Duke().run();
    }
}
