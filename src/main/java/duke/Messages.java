package duke;

public class Messages {
    private static String DOTTED_LINE = "______________________________________________________________________________\n";

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

    public static void helpMessage() {
        printDivider();
        System.out.println("Hi! If you need help on how to use me you've come to the right place!");
        System.out.println("Here's the list of commands I can handle now:");
        System.out.println("1.List - list");
        System.out.println("2.Add todo - todo <description>");
        System.out.println("3.Add deadline - deadline <description> /<due>");
        System.out.println("4.Add event - event <description> /<due>");
        System.out.println("5.Set task as done - done <task index>");
        System.out.println("5.Delete task - delete <task index>");
        printDivider();
    }
}
