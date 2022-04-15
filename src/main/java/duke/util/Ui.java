package duke.util;

import java.util.Scanner;

public class Ui {
    private static final String INPUT_PROMPT = "$ ";
    private static final String MESSAGE_WALL = "------------------------------------------------------------";
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private static final String MESSAGE_FORMAT_EXCEPTION = "An exception has occurred:\n%s";

    private final Scanner SCANNER;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.SCANNER = new Scanner(System.in);
    }

    /**
     * Gets a non-empty user input.
     *
     * @return Non-empty user input.
     */
    public String getUserInput() {
        String input;
        do {
            System.out.print(INPUT_PROMPT);
            input = SCANNER.nextLine();
        } while (input.trim().isEmpty());

        return input;
    }

    /**
     * Prints message within horizontal lines.
     *
     * @param message The message to print.
     */
    public void printMessage(String message) {
        System.out.println(MESSAGE_WALL);
        System.out.println(message);
        System.out.println(MESSAGE_WALL);
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        printMessage(MESSAGE_WELCOME);
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        printMessage(MESSAGE_EXIT);
    }

    /**
     * Prints an exception.
     *
     * @param message The message to print.
     */
    public void printException(String message) {
        printMessage(String.format(MESSAGE_FORMAT_EXCEPTION, message));
    }
}
