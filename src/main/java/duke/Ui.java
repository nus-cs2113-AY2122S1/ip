package duke;

/**
 * Class that manages the user interface of Duke, namely the printing of messages
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /**
     * Prints welcome message when Duke is first loaded up
     */
    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints a horizontal line
     */
    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints goodbye message before stopping Duke
     */
    public static void printGoodBye() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
