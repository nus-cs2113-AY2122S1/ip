package duke;

import duke.exception.ArgumentNotFoundException;
import duke.exception.InvalidCommandException;
import duke.task.TaskManager;
import duke.task.TaskType;
import duke.util.Parser;
import duke.util.Ui;

public class Duke {

    public static final String USERNAME = "VeryImportantUsername";
    public static final String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String NUMBER_ERROR_MESSAGE = "☹ NO!!! Done should only be given a number!";
    public static final String ARGUMENTS_ERROR_MESSAGE = "☹ Oh no!!! Arguments or delimiter could not be found.";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";

    public static TaskManager taskList = new TaskManager();

    /** Gets a new instance of Ui class to interact with the user */
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
            }
        }
        uiInteract.printGoodbye();
    }

    /**
     *
     *
     * @param command
     * @throws ArrayIndexOutOfBoundsException if unable to find arguments
     * @throws NumberFormatException          if arguments is not a number is not a number
     */

    /**
     * Handles the user input and decide what actions to run
     *
     * @param command takes in a command from user
     * @throws ArgumentNotFoundException a specific delimiter was found in the arguments
     * @throws NumberFormatException     if the argument was parsed as an int but is not a number
     * @throws InvalidCommandException   the command doesn't exist
     * @throws IllegalArgumentException  the creation of arguments created for a task was not valid
     * @throws NullPointerException      no arguments were provided for a command
     */
    private static void handleCommand(String command)
            throws ArgumentNotFoundException, NumberFormatException,
            InvalidCommandException, IllegalArgumentException, NullPointerException {
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
            taskList.markTaskAsDone(uiInteract, parsed.getArgsAsIndex());
            break;
        case COMMAND_TODO:
            taskList.addTask(uiInteract, parsed, TaskType.TODO);
            break;
        case COMMAND_DEADLINE:
            taskList.addTask(uiInteract, parsed, TaskType.DEADLINE);
            break;
        case COMMAND_EVENT:
            taskList.addTask(uiInteract, parsed, TaskType.EVENT);
            break;
        default:
            throw new InvalidCommandException();
        }
    }

    private static void terminateProgram() {
        isRunning = false;
    }
}
