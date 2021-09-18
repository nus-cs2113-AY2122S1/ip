/**
 * Ui class handles interactions with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    public static String getLine() {
        return LINE;
    }

    /**
     * Welcome message to the user upon startup of the program.
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = LINE + "\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n";

        System.out.println(logo + greeting);
    }

    /**
     * Closing message to the user before exiting the program.
     */
    public static void bye() {
        echo("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the given input String enclosed between two horizontal lines.
     *
     * @param input String to be printed.
     */
    public static void echo(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);

    }
}
