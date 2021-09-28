import java.util.ArrayList;

public class Ui {
    public static final String longLine = "____________________________________________________________";

    public static void printLongLine() {
        System.out.println(longLine);
    }

    public static void welcome() {
        printLongLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLongLine();
    }

    public static void farewell() {
        printLongLine();
        System.out.println("Byebye! Hope to see you again soon!");
        printLongLine();
    }

    public static void invalidCommand(String userCommand) {
        printLongLine();
        System.out.println(userCommand + " is an invalid command, try again");
        printLongLine();
    }

    public static void missingDetails() {
        printLongLine();
        System.out.println("You missed out some details of your task!");
        printLongLine();
    }

    public static String taskAdded(ArrayList<Task> entries, int entriesCount) {
        printLongLine();
        System.out.println("Got it. I've added this task:");
        String toBeWritten = "[" + entries.get(entriesCount).getSymbol() + "] ["
                + entries.get(entriesCount).getStatusIcon() + "] " + entries.get(entriesCount).description;
        System.out.println("   " + toBeWritten);
        return toBeWritten;
    }

    public static void entriesCount(int entriesCount) {
        System.out.println("Now you have " + entriesCount + " tasks in the list.");
        printLongLine();
    }
}