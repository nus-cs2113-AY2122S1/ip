package duke;

public class Messages {
    private static String DOTTED_LINE = "___________________________________________________\n";

    public static void welcomeMessage() {
        printDivider();
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        printDivider();
    }

    public static void exitMessage() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void printDivider() {
        System.out.println(DOTTED_LINE);
    }
}
