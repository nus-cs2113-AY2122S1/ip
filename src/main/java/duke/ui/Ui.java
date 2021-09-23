package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static duke.message.Messages.BYE;
import static duke.message.Messages.DELETED_TASK;
import static duke.message.Messages.FINISHED_TASK;
import static duke.message.Messages.FOUND_TASK;
import static duke.message.Messages.HELP;
import static duke.message.Messages.LIST_TASK;
import static duke.message.Messages.LOGO;
import static duke.message.Messages.WELCOME;

/**
 * Interact with user includes printing messages to user and taking user input
 */
public class Ui {
    private static final String SEPARATOR = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
    private static final String LS = System.lineSeparator();
    private static final String INPUT = "input: ";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints logo and welcome message
     */
    public void printWelcome() {
        out.println(LOGO);
        out.println(WELCOME);
    }

    /**
     * Prints error for DukeException
     * @param e the error message for duke exception
     */
    public void printDukeException(String e) {
        out.println(e);
    }

    /**
     * Prints help menu
     */
    public void printHelp() {
        out.println(SEPARATOR + LS + HELP);
    }

    /**
     * Prints message for list task
     */
    public void printListTask() {
        out.println(SEPARATOR + LS + LIST_TASK);
    }

    /**
     * Prints multiple Strings in a single line
     * @param message the Strings to print
     */
    public void printToUser(String... message) {
        for (String m : message) {
            out.print(m);
        }
        out.println();
    }

    /**
     * Prints message when task is done
     */
    public void printFinishedTask() {
        out.println(SEPARATOR + LS + FINISHED_TASK);
    }

    /**
     * Prints message when task is deleted
     */
    public void printDeletedTask() {
        out.println(SEPARATOR + LS + DELETED_TASK);
    }

    /**
     * Prints message for tasks found by keyword
     */
    public void printFound() {
        out.println(SEPARATOR + LS + FOUND_TASK);
    }

    /**
     * Prints message before exiting program
     */
    public void printBye() {
        out.println(BYE);
    }

    /**
     * Takes in user input
     * @return String that user input
     */
    public String getUserInput() {
        out.print(SEPARATOR + LS + INPUT);

        return in.nextLine();
    }
}
