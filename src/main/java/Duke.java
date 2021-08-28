import java.util.Scanner;

public class Duke {
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
    public static String getUserInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    /**
     * Starts Duke, repeatedly waits for and executes user commands until exit command is received.
     */
    public static void interactWithUser() {
        boolean isInteracting = true;
        TaskManager taskManager = new TaskManager();

        while (isInteracting) {
            String userInput = getUserInput().strip();
            String[] words = userInput.split(" ");

            if (userInput.equals("bye")) {
                printExitMessage();
                isInteracting = false;
            } else if (userInput.equals("list")) {
                taskManager.printTasks();
            } else if (userInput.startsWith("done")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                taskManager.completeTask(taskIndex);
            }
            else if (userInput.startsWith("todo")) {
                taskManager.addTask(userInput, TaskType.TODO);
            }
            else if (userInput.startsWith("deadline")) {
                taskManager.addTask(userInput, TaskType.DEADLINE);
            }
            else if (userInput.startsWith("event")) {
                taskManager.addTask(userInput, TaskType.EVENT);
            }
            else {
                printInvalidCommandMessage();
            }
        }
    }
}
