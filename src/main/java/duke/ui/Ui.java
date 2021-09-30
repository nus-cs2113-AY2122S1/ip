package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.data.exception.DukeException;
import duke.message.Messages;


public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner in;

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Returns true if the user input line is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace
     *
     * @return command (raw) entered by the user
     */
    public String readCommand() {
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        return fullInputLine.trim();
    }


    /**
     * Print a line as divider
     */
    public void showLine() {
        System.out.println(DIVIDER);

    }

    public void showWelcome() {
        showToUser(Messages.MESSAGE_WELCOME, DIVIDER, Messages.MESSAGE_GREET, DIVIDER);

    }

    public void showHelp() {
        showToUser(Messages.MESSAGE_HELP);

    }

    public void showGoodbyeMessage() {
        showToUser(Messages.MESSAGE_GOODBYE);
    }

    /**
     * Print uncertain number of strings
     */
    private void showToUser(String... message) {
        for (String m : message) {
            String formatted = m.replace("\n", System.lineSeparator());
            System.out.println(formatted);
        }
    }

    public void showError(String errorInformation) {
        System.out.println(errorInformation);
        System.exit(0);
    }

    /**
     * Deal with all kinds of error
     */
    public void showLoadingError() {
        showToUser(Messages.MESSAGE_LOADING_ERROR, DIVIDER);

    }

}
