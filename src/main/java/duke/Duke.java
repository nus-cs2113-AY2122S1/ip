package duke;

import duke.exception.InvalidCommandException;
import duke.task.TaskManager;
import duke.task.TaskType;
import java.security.InvalidParameterException;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    /* User input is seperated by an empty space */
    public static final String USER_INPUT_SEPERATOR = " ";

    /* User command list */
    public static final String USER_COMMAND_LIST = "LIST";
    public static final String USER_COMMAND_TODO = "TODO";
    public static final String USER_COMMAND_DEADLINE = "DEADLINE";
    public static final String USER_COMMAND_EVENT = "EVENT";
    public static final String USER_COMMAND_DONE = "DONE";
    public static final String USER_COMMAND_BYE = "BYE";
    public static final String USER_COMMAND_HELP = "HELP";
    public static final String USER_COMMAND_DELETE = "DELETE";

    /* A nicely formatted line */
    private static final String LINE = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
    private static final String WELCOME_MESSAGE = "Hello! I'm Gaben.\n"
            + "Steam sales is coming! Prepare your wallet.\n"
            + "Anyways, what can I do for you today?";
    private static final String EXIT_MESSAGE = "Thank you for using Gaben.\n"
            + "Remember to keep your wallet stacked before using me again!";
    private static final String HELP_MESSAGE = "The following commands accepted are: "
            + "LIST (Show the list of task)\n"
            + "TODO <description> (Create a task with todo tag)\n"
            + "DEADLINE <description> /by <date and time> (Create a task with deadline tag)\n"
            + "EVENT <description> /at <date and time> (Create a task with event tag)\n"
            + "DONE <index of task> (To mark indicated task as completed)\n"
            + "BYE (End program)\n"
            + "HELP (List out available commands)";


    /**
     * Prints given string in the middle of 2 horizontal lines.
     *
     * @param message String to be printed
     */
    public static void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public static void main(String[] args) throws InvalidParameterException {

        // Print welcome message to user
        printMessage(WELCOME_MESSAGE);

        // Create task manager to manage task given by user
        TaskManager taskManager = new TaskManager();
        taskManager.loadData();

        // Create a scanner to read user input
        Scanner in = new Scanner(System.in);

        // Boolean to allow continuous listening of user input
        boolean exit = false;

        // Listen for user input and do commands given by user till user wants to exit program
        while (!exit) {
            String userInput = in.nextLine();
            String[] userInputArray = userInput.split(USER_INPUT_SEPERATOR, 2);
            String userCommand = userInputArray[0].toUpperCase(Locale.ROOT);
            try {
                switch (userCommand) {
                case USER_COMMAND_LIST:
                    taskManager.listTask();
                    break;
                case USER_COMMAND_BYE:
                    exit = true;
                    break;
                case USER_COMMAND_DEADLINE:
                    taskManager.addTask(userInputArray, TaskType.DEADLINE);
                    break;
                case USER_COMMAND_EVENT:
                    taskManager.addTask(userInputArray, TaskType.EVENT);
                    break;
                case USER_COMMAND_TODO:
                    taskManager.addTask(userInputArray, TaskType.TODO);
                    break;
                case USER_COMMAND_DONE:
                    taskManager.completeTask(Integer.parseInt(userInputArray[1]));
                    break;
                case USER_COMMAND_DELETE:
                    taskManager.deleteTask(Integer.parseInt(userInputArray[1]));
                    break;
                case USER_COMMAND_HELP:
                    printMessage(HELP_MESSAGE);
                    break;

                default:
                    throw new InvalidCommandException("Invalid Command Given.\n" + HELP_MESSAGE);
                }
            } catch (InvalidCommandException e) {
                printMessage(e.getMessage());
            } catch (NumberFormatException e) {
                printMessage("Please give a number for the following command: " + userCommand + " <number>");
            } catch (IndexOutOfBoundsException e) {
                printMessage("Please give command in the following format, you are missing something.\n" + HELP_MESSAGE);
            }
        }
        taskManager.saveData();
        printMessage(EXIT_MESSAGE);

    }
}
