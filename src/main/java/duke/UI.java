package duke;

import duke.task.Task;
import java.util.Scanner;

public class UI {

    public static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLineSpacer();
        System.out.println("\tHey, what's up!\n" + "\tWhat can I help you with today?");
        printLineSpacer();
    }

    public static void printBye() {
        System.out.println("\tAight. See you soon mate.");
        printLineSpacer();
    }

    public static void printDelete(int numOfTasks, String userEntry) {
        int currentNumOfTasks = numOfTasks - 1;
        System.out.println("\tAlright, the following task has been removed");
        System.out.println("\t" + userEntry);
        System.out.println("\tNow you have " + currentNumOfTasks + " tasks left");
    }

    public static void printDone(String userEntry) {
        System.out.println("\tGood job on completing a task!");
        System.out.println("\t" + userEntry);

    }

    public static void printAdd(int numOfTasks, Task userEntry) {
        System.out.println("\tAight, I've added the following task to your list:");
        System.out.println("\t" + userEntry.toString());
        System.out.println("\t" + "Now you have " + numOfTasks + " tasks in your list");
    }

    public static String getUserInput() {
        String userLineInput;
        Scanner scanner = new Scanner(System.in);
        userLineInput = scanner.nextLine();
        return userLineInput;
    }

    public static void printLineSpacer() {
        System.out.println("\t**************************************************");
    }
}
