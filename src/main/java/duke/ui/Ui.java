package duke.ui;

import duke.text.Text;
import java.util.Scanner;

public class Ui extends Text {

    private final Scanner in;

    /**
     * A constructor to initialise ui.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * A getter to read a user input.
     *
     * @return String of user input.
     */
    public String getUserCommand() {
        System.out.print(GET_COMMAND);
        return in.nextLine();
    }

    /**
     * A formatter to set the format for printing every statement.
     * A line divider before and after a message to print.
     *
     * @param message a String to be printed.
     */
    public static void printWithLine(String message) {
        System.out.print(LINE + message + LINE);
    }

    /**
     * Prints the introductory message when duke runs.
     */
    public void printIntroduction() {
        printWithLine(LOGO);
        printWithLine(HELLO_MESSAGE);
    }

    /**
     * Prints the help list of commands when prompted.
     */
    public static void printHelp() {
        printWithLine(HELP_LIST);
    }

    /**
     * Prints goodbye message when prompted before exiting the program.
     */
    public static void printBye() {
        printWithLine(GOODBYE_MESSAGE);
    }
}
