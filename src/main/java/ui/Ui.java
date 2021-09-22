package ui;

import java.util.Scanner;

public class Ui {

    public static final String GREET_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String EXIT_MESSAGE = " Bye. Hope to see you again soon!";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static Scanner in = new Scanner(System.in);

    /**
     * Greets the user by printing some introductory messages
     */
    public static void greet() {
        printLine();
        System.out.println(GREET_MESSAGE);
        printLine();
    }

    /**
     * Greets the user goodbye and the code finishes its execution.
     */
    public static void greetBye() {
        System.out.println(EXIT_MESSAGE);
        printLine();
    }


    /**
     * Prints a line on the screen
     */
    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static String getCommand() {
        String userInput = in.nextLine();
        if (userInput.isBlank()) {
            return getCommand();
        }
        return userInput;
    }

}

