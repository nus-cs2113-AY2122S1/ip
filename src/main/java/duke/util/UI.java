package duke.util;

/**
 * Represents most of the visuals of the program. Has various functions to print text to the command line.
 */
public class UI {
    public static final String DIVIDING_LINE = "________________________________________";

    /**
     * Prints the opening message to the command line.
     */
    public void printGreeting() {
        System.out.println(DIVIDING_LINE);
        System.out.println("Greetings, human! I'm Duke. \nWhat can I do for you?");
        System.out.println(DIVIDING_LINE);
    }

    /**
     * Prints the closing message to the command line.
     */
    public void printFarewell() {
        System.out.println(DIVIDING_LINE);
        System.out.println("Closing Duke. Have a nice day!");
        System.out.println(DIVIDING_LINE);
    }

    /**
     * Prints a dividing line.
     */
    public void printDivLine() {
        System.out.println(DIVIDING_LINE);
    }

    /**
     * Shows an error on the command line whenever a DukeException is thrown.
     */
    public void showError(String error) {
        System.out.println("DukeException: " + error);
    }
}
