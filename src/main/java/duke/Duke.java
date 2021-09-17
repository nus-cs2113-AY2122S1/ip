package duke;

import duke.exception.ArgumentNotFoundException;
import duke.exception.InvalidCommandException;
import duke.task.TaskManager;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import java.io.IOException;

public class Duke {

    public static final String USERNAME = "VeryImportantUsername";

    public static final String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, "
            + "but I don't know what that means "
            + ":-(";
    public static final String NUMBER_ERROR_MESSAGE = "☹ NO!!! done/delete "
            + "should only be given a number!";
    public static final String ARGUMENTS_ERROR_MESSAGE = "☹ Oh no!!! Arguments or "
            + "delimiter could not be found.";

    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";

    public static TaskManager taskList = new TaskManager();
    public static Storage store = new Storage();
    public static Ui uiInteract = new Ui(USERNAME);

    public static boolean isRunning = true;


    public static void main(String[] args) {
        uiInteract.printBanner();
        printMenuPrompt();
    }

    /**
     * Handles the user input and loop logic Calls handleCommand and terminates when isRunning is false
     */
    private static void printMenuPrompt() {
        try {
            store.loadFile(taskList);
        } catch (IOException | IllegalArgumentException fileException) {
            uiInteract.printMessage("Failed to read or create data file!");
            System.exit(1);
        }
        while (isRunning) {
            //Printing user prompt
            uiInteract.printPrompt();
            // Reading user input
            String userInput = uiInteract.getUserInput();
            //Handling Exceptions
            try {
                handleCommand(userInput);
            } catch (ArgumentNotFoundException | NullPointerException errorMessage) {
                uiInteract.printMessage(ARGUMENTS_ERROR_MESSAGE);
            } catch (NumberFormatException invalidParsing) {
                uiInteract.printMessage(NUMBER_ERROR_MESSAGE);
            } catch (IllegalArgumentException invalidArguments) {
                uiInteract.printMessage("☹ OOPS!!! " + invalidArguments.getMessage());
            } catch (InvalidCommandException invalidCommand) {
                uiInteract.printMessage(UNKNOWN_COMMAND_MESSAGE);
            } catch (IOException saveException) {
                uiInteract.printMessage("Could not update file or directory!!");
            }
        }
        uiInteract.printGoodbye();
    }

    /**
     * Handles the user input and decide what actions to run
     *
     * @param command takes in a command from user
     * @throws ArgumentNotFoundException a specific delimiter was found in the arguments
     * @throws NumberFormatException     if the argument was parsed as an int but is not a number
     * @throws InvalidCommandException   the command doesn't exist
     * @throws IllegalArgumentException  the creation of arguments created for a task was not valid
     * @throws NullPointerException      no arguments were provided for a command
     * @throws IOException               when storing data to file has failed
     */
    private static void handleCommand(String command)
            throws ArgumentNotFoundException, NumberFormatException,
            InvalidCommandException, IllegalArgumentException, NullPointerException,
            IOException {

        // Attempting to parse the command
        Parser parsed = new Parser(command);

        if (parsed.getCommand() == null) {
            uiInteract.printCommandHelp();
            return;
        }

        switch (parsed.getCommand()) {
        case COMMAND_LIST:
            taskList.listTasks(uiInteract);
            break;
        case COMMAND_BYE:
            terminateProgram();
            break;
        case COMMAND_DONE:
            int taskDoneIndex = parsed.getArgsAsIndex();
            taskList.markTaskAsDone(uiInteract, taskDoneIndex);
            break;
        case COMMAND_DELETE:
            int taskDeleteIndex = parsed.getArgsAsIndex();
            taskList.deleteTask(uiInteract, taskDeleteIndex);
            break;
        default:
            taskList.addTask(uiInteract, parsed);
        }
        store.saveFile(taskList);
    }

    private static void terminateProgram() {
        isRunning = false;
    }
}
