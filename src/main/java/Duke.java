import java.util.Scanner;

public class Duke {

    /** Username for the chatbot prompt */
    public static final String USERNAME = "VeryImportantUsername";
    public static final String ERROR_MESSAGE = "Error! Invalid input please try again!";


    /** Stores all task added by the user */
    public static TaskManager taskList = new TaskManager();

    /** Gets a new instance of UI class to interactive with user */
    public static UI UIInteract = new UI(USERNAME);

    public static boolean isRunning = true;


    public static void main(String[] args) {
        UIInteract.printBanner();
        printMenuPrompt();
    }

    /**
     * Handles the user input and loop logic Calls handleCommand and terminates when isRunning is false
     */
    private static void printMenuPrompt() {
        while (isRunning) {
            //Printing user prompt
            UIInteract.printPrompt();
            // Reading user input
            String userInput = UIInteract.getUserInput();
            try {
                handleCommand(userInput);
            } catch (Exception e) {
                UIInteract.printMessage(ERROR_MESSAGE);
            }
        }
        UIInteract.printGoodbye();
    }

    /**
     * Handles the user input and decide what actions to run
     *
     * @param command takes in a command from user
     * @throws ArrayIndexOutOfBoundsException if unable to find arguments
     * @throws NumberFormatException          if arguments is not a number is not a number
     */
    private static void handleCommand(String command) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        // Attempting to parse the command
        Parser parsed = new Parser(command);
        if (parsed.getCommand() == null) {
            UIInteract.printCommandHelp();
            return;
        }
        switch (parsed.getCommand()) {
        case "list":
            UIInteract.listTasks(taskList);
            break;
        case "bye":
            terminateProgram();
            break;
        case "done":
            taskList.markTaskAsDone(UIInteract, parsed.getArgsAsIndex());
            break;
        case "todo":
            taskList.addTask(UIInteract, parsed, TaskType.TODO);
            break;
        case "deadline":
            taskList.addTask(UIInteract, parsed, TaskType.DEADLINE);
            break;
        case "event":
            taskList.addTask(UIInteract, parsed, TaskType.EVENT);
            break;
        default:
            UIInteract.printCommandHelp();
        }
    }

    private static void terminateProgram() {
        isRunning = false;
    }
}
