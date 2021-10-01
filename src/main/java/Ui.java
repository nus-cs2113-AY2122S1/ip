import java.util.ArrayList;

/**
 * Handles interactions with the user by printing the appropriate response to the user's input
 */
public class Ui {
    public static final String longLine = "____________________________________________________________";

    /**
     * Prints a long line across the screen as a visual indicator to the user of a new section
     */
    public static void printLongLine() {
        System.out.println(longLine);
    }

    /**
     * Prints a friendly introductory message to the user to make them comfortable with using the app
     */
    public static void welcome() {
        printLongLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLongLine();
    }

    /**
     * Prints a goodbye message to the user to make them comfortable with using the app
     */
    public static void farewell() {
        printLongLine();
        System.out.println("Byebye! Hope to see you again soon!");
        printLongLine();
    }

    /**
     * Indicates to the user that the command they typed is not recognised
     *
     * @param userCommand User's command
     */
    public static void invalidCommand(String userCommand) {
        printLongLine();
        System.out.println("Hey! That's an invalid command, try again");
        printLongLine();
    }

    /**
     * Indicates to the user that their task input is incomplete and requires more details e.g. deadline, description
     */
    public static void missingDetails() {
        printLongLine();
        System.out.println("You missed out some details of your task!");
        printLongLine();
    }

    /**
     * Indicates to the user that their task has been added as an element in the entries ArrayList
     * Returns the message to be written to the target file
     *
     * @param entries ArrayList of Task entries
     * @param entriesCount Number of elements in the entries ArrayList
     * @return toBeWritten Message to be written to the target file
     */
    public static String taskAdded(ArrayList<Task> entries, int entriesCount) {
        printLongLine();
        System.out.println("Got it. I've added this task:");
        String toBeWritten = "[" + entries.get(entriesCount).getSymbol() + "] ["
                + entries.get(entriesCount).getStatusIcon() + "] " + entries.get(entriesCount).description;
        System.out.println("   " + toBeWritten);
        return toBeWritten;
    }

    /**
     * Indicates to the user the number of elements are in the entries ArrayList
     *
     * @param entriesCount Number of elements in the entries ArrayList
     */
    public static void entriesCount(int entriesCount) {
        System.out.println("Now you have " + entriesCount + " tasks in the list.");
        printLongLine();
    }
}

