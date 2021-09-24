package triss.ui;

import java.util.Scanner;

public class Ui {

    /** Logo shown during startup */
    private static final String LOGO = "████████ ██████  ██ ███████ ███████ \n" +
            "   ██    ██   ██ ██ ██      ██      \n" +
            "   ██    ██████  ██ ███████ ███████ \n" +
            "   ██    ██   ██ ██      ██      ██ \n" +
            "   ██    ██   ██ ██ ███████ ███████ \n";

    /** String of underscores to separate user input and Triss output */
    private final String SEPARATOR_LINE = "____________________________________________________________";

    /** Scanner to read user input */
    private static Scanner in;

    /** Static string variable to store user input across UIs */
    private static String userInput;

    public Ui() {
        in = createNewInputReader();
    }

    /**
     * Gets stored user input.
     * @return Stored user input as string.
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * Create a new Scanner to read user input.
     * @return A new Scanner.
     */
    private Scanner createNewInputReader() {
        return new Scanner(System.in);
    }

    /**
     * Read the next line of user input.
     * If the user input is blank, asks user for input again.
     */
    public void readUserInput() {
        /* String variable to store user input */
        userInput = in.nextLine();

        while (userInput.isBlank()) {
            printLine("Stop with the silent treatment! Say something?");
            printLine(SEPARATOR_LINE);
            userInput = in.nextLine();
        }
    }

    /**
     * Print a string, then terminates the line.
     * @param s The string to be printed.
     */
    public void printLine(String s) {
        System.out.println(s);
    }

    /**
     * Print a separator line, then terminates the line.
     */
    public void printSeparatorLine() {
        printLine(SEPARATOR_LINE);
    }

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage() {
        printLine("Hello from\n" + LOGO);
        printLine(SEPARATOR_LINE);
        printLine("Hello! I'm Triss :)");
        printLine("What can I do for you?");
        printLine(SEPARATOR_LINE);
    }

    /**
     * Prints shutdown message.
     */
    public void printShutdownMessage() {
        // If user said "bye", update hasUserSaidBye and print closing phrase
        printLine("Thanks for coming. Auf wiedersehen!");
    }
}
