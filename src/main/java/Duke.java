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
    public static void printLine() {
        System.out.println("  ──────────────────────────────");
    }

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
        Task[] tasks = new Task[100];
        int tasksCount = 0;

        while (isInteracting) {
            String userInput = getUserInput().strip();
            String[] words = userInput.split(" ");

            switch (words[0]) {
            case "bye":
                printExitMessage();
                isInteracting = false;
                break;
            case "list":
                printLine();
                System.out.println("  Here are your tasks:");
                for (int i = 0; i < tasksCount; i++) {
                    System.out.print("    " + (i + 1) + ".");
                    System.out.println(tasks[i].toString());
                }
                printLine();
                break;
            case "done":
                int taskIndex = Integer.parseInt(words[1]) - 1;
                tasks[taskIndex].setCompleted();
                printLine();
                System.out.print("  Ok! I've marked this task as done:\n    ");
                System.out.println(tasks[taskIndex].toString());
                printLine();
                break;
            default:
                printLine();
                System.out.println("  added: " + userInput);
                printLine();
                tasks[tasksCount] = new Task(userInput);
                tasksCount++;
                break;
            }
        }
    }
}
