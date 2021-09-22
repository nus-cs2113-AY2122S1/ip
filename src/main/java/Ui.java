/**
 * Contains all UI related methods.
 * Deals the visuals of Duke.
 */
public class Ui {

    public static final String LINE = "____________________________________________________________\n";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints a message to the user.
     *
     * @param message Message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println(LINE + message + LINE);
    }

    /**
     * Prints a greeting message to the user.
     */
    public static void printGreeting() {
        System.out.println("Hello from\n"
                + LOGO);
        String message = " Hello! I'm Duke\n"
                + " What can I do for you?\n";
        printMessage(message);
    }

    /**
     * Prints a farewell message to the user.
     */
    public static void printFarewell() {
        String message = " Bye. Hope to see you again soon!\n";
        printMessage(message);
    }

}
