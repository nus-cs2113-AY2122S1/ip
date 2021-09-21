package IzzIbot;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;

    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String HELLO_MESSAGE = "Task manager IzzIbot at your service.";
    public static final String BYE_MESSAGE = "You've terminated IzzIbot. Have a good day!";

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public Ui() {
        this(System.in, System.out);
    }

    /**
     * From addressbook-level2
     * Prompts for the command and reads the text entered by the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        out.print("What would you like to do?\n");
        String fullInputLine = in.nextLine();

        return fullInputLine;
    }

    /**
     * Prints text within two horizontal lines.
     * @param text string to be printed.
     */
    public void printWithLines(String text) {
        out.println(HORIZONTAL_LINE);
        out.println(text);
        out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints welcome message upon running IzzIbot.
     */
    public void printHelloMessage() {
        printWithLines(HELLO_MESSAGE);
    }

    /**
     * Prints exit message upon terminating IzzIbot.
     */
    public void printByeMessage() {
        printWithLines(BYE_MESSAGE);
    }
}
