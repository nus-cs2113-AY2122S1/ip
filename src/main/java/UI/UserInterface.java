package UI;

import java.util.Scanner;

/**
 * Deals with the communication with the user
 */
public class UserInterface {
    Scanner line = new Scanner(System.in);
    public static final String BORDER = "_________________________________________\n";
    private static final String GREETING = "****************************\n"
            + "*  ____             ____   *\n"
            + "* |  _ \\    ____   |  _ \\  *\n"
            + "* | |_| |  / __ \\  | |_| | *\n"
            + "* |  _  | | |  | | |  _  | *\n"
            + "* | |_| | | |__| | | |_| | *\n"
            + "* |____/   \\____/  |____/  *\n"
            + "****************************\n"
            + BORDER
            + "Have no fear, Bob is here!\n"
            + "What is it that you require?\n"
            + "For a list of commands, type: help\n"
            + BORDER;

    public static final String EXIT_MESSAGE = BORDER + "Bye. Have a nice day!\n" + BORDER;

    public UserInterface() {
        printGreeting();
    }

    /**
     * Prints the greeting message
     */
    public void printGreeting() {
        System.out.println(GREETING);
    }

    /**
     * Prints the exit message
     */
    public void printGoodbye() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Reads the user input
     * @return the trimmed user input
     */
    public String getUserInput() {
        return line.nextLine().trim();
    }

    /**
     * Prints the output of the command executed to the user
     * @param output the output of the command executed
     */
    public void showOutputToUser(String output) {
        System.out.println(BORDER + output + BORDER);
    }

    /**
     * Prints the error message from the exceptions caught from the user input
     * @param errorMessage the error description
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println(BORDER + errorMessage + "\n" + BORDER);
    }
}
