package duke;

import java.util.Scanner;

public class Ui {
    Scanner inputScanner = new Scanner(System.in);

    /**
     * Gets user input and returns it as a String.
     *
     * @return the String containing the user input.
     */
    public String readInput() {
        String line;
        line = inputScanner.nextLine();
        return line;
    }

    /**
     * Prints a horizontal line in the terminal.
     */
    public void printLine() {
        System.out.println("  ──────────────────────────────");
    }

    /**
     * Prints the application start message in the terminal.
     */
    public void printStartMessage() {
        printLine();
        System.out.println("  Hello! I'm Duke\n  How may I assist you");
        printLine();
    }

    /**
     * Prints the application exit message in the terminal.
     */
    public void printExitMessage() {
        printLine();
        System.out.println("  Goodbye! Hope to see you soon!");
        printLine();
    }

    /**
     * Prints the error message
     *
     * @param s The error message to be printed
     */
    public void printErrorMessage(String s) {
        printLine();
        System.out.println("  " + s);
        printLine();
    }


    /**
     * Prints the Duke application logo in the terminal.
     */
    public void printAppLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
