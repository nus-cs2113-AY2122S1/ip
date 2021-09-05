public class Duke {

    /**
     * Username for the chatbot prompt
     */
    public static final String USERNAME = "VeryImportantUsername";
    public static final String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String NUMBER_ERROR_MESSAGE = "☹ NO!!! Done should only be given a number!";
    public static final String ARGUMENTS_ERROR_MESSAGE = "☹ Oh no!!! Arguments or delimiter could not be found.";


    /**
     * Stores all task added by the user
     */
    public static TaskManager taskList = new TaskManager();

    /**
     * Gets a new instance of UI class to interactive with user
     */
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
     * Handles the user input and decide what actions to run
     *
     * @param command takes in a command from user
     * @throws ArrayIndexOutOfBoundsException if unable to find arguments
     * @throws NumberFormatException          if arguments is not a number is not a number
     */
    private static void handleCommand(String command)
            throws ArgumentNotFoundException, NumberFormatException,
            InvalidCommandException, IllegalArgumentException,NullPointerException{
        // Attempting to parse the command
        Parser parsed = new Parser(command);
        if (parsed.getCommand() == null) {
            uiInteract.printCommandHelp();
            return;
        }
        switch (parsed.getCommand()) {
        case "list":
            taskList.listTasks(uiInteract);
            break;
        case "bye":
            terminateProgram();
            break;
        case "done":
            taskList.markTaskAsDone(uiInteract, parsed.getArgsAsIndex());
            break;
        case "todo":
            taskList.addTask(uiInteract, parsed, TaskType.TODO);
            break;
        case "deadline":
            taskList.addTask(uiInteract, parsed, TaskType.DEADLINE);
            break;
        case "event":
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
