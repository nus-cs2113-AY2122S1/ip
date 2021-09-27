package duke.ui;

public class Ui {

    private final static String WELCOME_GREETING = "Howdy there! I'm Fluke";
    private final static String WELCOME_ASK = "What can I do for you today master?";
    private final static String BYE_MESSAGE = "Bye. Hope to serve you again master!";

    /**
     * Prints a welcome message on the console
     */
    public static void welcomeMessage() {
        printLine();
        System.out.println(WELCOME_GREETING);
        System.out.println(WELCOME_ASK);
        printLine();
    }

    /**
     * Prints a bye message on the console
     */
    public static void byeMessage() {
        printLine();
        System.out.println(BYE_MESSAGE);
        printLine();
    }

    /**
     * Prints a line on the console
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
