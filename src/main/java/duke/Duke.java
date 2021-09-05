package duke;

import duke.command.Command;
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
            String userInput = getUserInput(in).stripLeading();
            try {
                Command command = CommandParser.parse(userInput);
                command.execute(taskManager);
                isInteracting = !command.isExit();
            } catch (DukeException e) {
                printLine();
                System.out.println("  " + e.getMessage());
                printLine();
            }
        }
        printExitMessage();
    }
}
