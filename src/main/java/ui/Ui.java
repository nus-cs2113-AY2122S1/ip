package ui;

import java.util.Scanner;

public class Ui {
    /**
     * Storing some messages as String.
     */
    public static final String GREET_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String EXIT_MESSAGE = " Bye. Hope to see you again soon!";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Creating an object of the Scanner class to facilitate accepting input from user.
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Greets the user by printing some introductory message.
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
        System.exit(0);
    }


    /**
     * Prints a line on the screen
     */
    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Returns the command accepted from the user.
     *
     * @return userInput is the command entered by the user.
     */
    public static String getCommand() {
        String userInput = sc.nextLine();
        if (userInput.isBlank()) {
            return getCommand();
        }
        return userInput;
    }

}

