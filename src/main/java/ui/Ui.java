package ui;

import java.util.Scanner;


/**
 * The Ui object will be responsible for handling the output to the screen, as well as reading user inputs.
 */
public class Ui {

    private static final String ERROR_HEADER = "Error: ";
    private static final String SUCCESS_HEADER = "Success: ";
    private static final String USER_INPUT_HEADER = "duke: $ ";
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETINGS = "Hello! I'm Duke\n" +
                                            "What can I do for you?\n";
    private static final String USER_LEAVE_MESSAGE = "Bye. Hope to see you again soon!\n";

    /**
     * Get the user input.
     * @return The command the user entered
     */
    public String getUserInput() {
        System.out.print(USER_INPUT_HEADER);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints the default greeting message on program start.
     */
    public void printGreeting() {
        System.out.println(LOGO);
        System.out.println(GREETINGS);
    }

    /**
     * Prints the farewell message when the user terminates the program.
     */
    public void printBye() {
        System.out.println(USER_LEAVE_MESSAGE);
    }

    /**
     * Prints the message when a command is executed successfully.
     * @param successMessage The success message
     */
    public void printSuccessMessage(String successMessage) {
        System.out.println(SUCCESS_HEADER + successMessage);
    }

    /**
     * Prints the error message when an exception/error is encountered.
     * @param errorMessage The error message
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_HEADER + errorMessage);
    }

    /**
     * Prints a message to the screen.
     * @param message The message to print
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
