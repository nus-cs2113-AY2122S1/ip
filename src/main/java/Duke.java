import java.util.Scanner;

public class Duke {
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK_DONE = "done";
    public static final String COMMAND_NEW_TODO = "todo";
    public static final String COMMAND_NEW_DEADLINE = "deadline";
    public static final String COMMAND_NEW_EVENT = "event";


    public static void main(String[] args) {
        printStartMessage();
        interactWithUser();
    }

    /**
     * Prints the Duke application logo in the terminal.
     */
    public static void printAppLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a horizontal line in the terminal.
     */
    private static void printLine() {
        System.out.println("  ──────────────────────────────");
    }

    /**
     * Prints the application start message in the terminal.
     */
    public static void printStartMessage() {
        printLine();
        System.out.println("  Hello! I'm Duke\n  How may I assist you");
        printLine();
    }

    /**
     * Prints the application exit message in the terminal.
     */
    public static void printExitMessage() {
        printLine();
        System.out.println("  Goodbye! Hope to see you soon!");
        printLine();
    }

    /**
     * Prints the invalid command message in the terminal.
     */
    public static void printInvalidCommandMessage() {
        printLine();
        System.out.println("  Error: Invalid command");
        printLine();
    }

    /**
     * Gets user input and returns it as a String.
     *
     * @return the String containing the user input.
     */
    public static String getUserInput(Scanner in) {
        String line;
        line = in.nextLine();
        return line;
    }

    /**
     * Starts Duke, repeatedly waits for and executes user commands until exit command is received.
     */
    public static void interactWithUser() {
        boolean isInteracting = true;
        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);

        while (isInteracting) {
            String userInput = getUserInput(in).strip();
            String[] words = userInput.split(" ");

            if (userInput.equals(COMMAND_EXIT)) {
                printExitMessage();
                isInteracting = false;
            } else if (userInput.equals(COMMAND_LIST)) {
                taskManager.printTasks();
            } else if (userInput.startsWith(COMMAND_MARK_DONE)) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                taskManager.completeTask(taskIndex);
            } else if (userInput.startsWith(COMMAND_NEW_TODO)) {
                taskManager.addTask(userInput, TaskType.TODO);
            } else if (userInput.startsWith(COMMAND_NEW_DEADLINE)) {
                taskManager.addTask(userInput, TaskType.DEADLINE);
            } else if (userInput.startsWith(COMMAND_NEW_EVENT)) {
                taskManager.addTask(userInput, TaskType.EVENT);
            } else {
                printInvalidCommandMessage();
            }
        }
    }
}
