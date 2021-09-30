package duke;

public class Ui {

    private static final String LINE = "-------------------------------------------------------------------\n";
    private static final String GREET_USER = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Prints out welcome message to greet user.
     */
    public static void greetUser() {
        System.out.println(GREET_USER + "\n \n" + LINE);
    }

    /**
     * Prints out good bye message to user.
     */
    public static void byeUser() {
        System.out.println(EXIT_MESSAGE + "\n" + LINE);
    }

    /**
     * Prints out a divider line.
     */
    public static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints out a error message for file loading.
     */
    public void showLoadingError() {
        System.out.println("No file found! Creating a new file");
    }
}
