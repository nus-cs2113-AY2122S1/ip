package ui;

import java.io.PrintStream;
import java.util.Scanner;
import static common.Message.MESSAGE_LOGO;
import static common.Message.MESSAGE_WELCOME;

/**
 * Object which contains IO streams and has methods
 * that allow reading of user input and printing of
 * program responses, outputs and errors
 */
public class TextUI {
    private final Scanner in;
    private final PrintStream out;
    private final static String prefix = "> ";

    public TextUI() {
        in = new Scanner(System.in);
        out = System.out;
        out.println(MESSAGE_LOGO);
        out.println(MESSAGE_WELCOME);
    }

    /**
     * Reads in full line terminated by newline from
     * user input
     * @return string Returns full line entered by user
     */
    public String getCommand() {
        out.print(prefix);
        String input = in.nextLine();
        return input;
    }

    /**
     * Prints message to command line output
     * @param message This is the message to be printed
     */
    public void showMessage(String message) {
        out.println(message);
    }
}
