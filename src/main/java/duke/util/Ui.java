package duke.util;

import java.util.Scanner;

/**
 * Represents the user interface of Duke
 * This class is responsible for printing and receiving inputs of the user
 */
public class Ui {
    private static final String SEPARATING_LINE = "\t-------------------------------------";
    private static final String GREET_MESSAGE = "Yo what's up I'm Jesse. Need help?";

    private Scanner scanner = new Scanner(System.in);

    /**
     * Print message with line separators and tabs
     *
     * @param msg the message to print
     */
    public void print(String msg) {
        System.out.println(SEPARATING_LINE);
        System.out.println("\t" + msg);
        System.out.println(SEPARATING_LINE);
    }

    /**
     * Reads the user's input command
     *
     * @return the user's input command as String
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints greet message when the program starts
     *
     */
    public void greet() {
        print(GREET_MESSAGE);
    }
}
