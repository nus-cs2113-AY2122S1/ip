package duke;

import duke.task.Task;

import java.util.Scanner;
import java.util.List;

public class UI {

    /**
     * Prints message shown when first starting up duke.
     */
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

    /**
     * Prints message shown when exiting duke.
     */
    public static void printBye() {
        System.out.println("\tAight. See you soon mate.");
        printLineSpacer();
    }

    /**
     * Prints message shown when deleting a task from the task list.
     *
     * @param numOfTasks Current total number of tasks in the task list.
     * @param userEntry String description of the entry to be deleted.
     */
    public static void printDelete(int numOfTasks, String userEntry) {
        printLineSpacer();
        int currentNumOfTasks = numOfTasks - 1;
        System.out.println("\tAlright, the following task has been removed");
        System.out.println("\t" + userEntry);
        System.out.println("\tNow you have " + currentNumOfTasks + " tasks left");
    }

    /**
     * Prints message shown when marking a task as done.
     *
     * @param userEntry String description of the entry to be marked as done.
     */
    public static void printDone(String userEntry) {
        printLineSpacer();
        System.out.println("\tGood job on completing a task!");
        System.out.println("\t" + userEntry);

    }

    /**
     * Prints message shown when adding a task to the task list.
     *
     * @param numOfTasks Current total number of tasks in the task list.
     * @param userEntry User entry that is being added to the task list.
     */
    public static void printAdd(int numOfTasks, Task userEntry) {
        printLineSpacer();
        System.out.println("\tAight, I've added the following task to your list:");
        System.out.println("\t" + userEntry.toString());
        System.out.println("\t" + "Now you have " + numOfTasks + " tasks in your list");
    }

    /**
     * Gets the user input and returns the string.
     *
     * @return Returns the user input as a string.
     */
    public static String getUserInput() {
        String userLineInput;
        Scanner scanner = new Scanner(System.in);
        userLineInput = scanner.nextLine();
        return userLineInput;
    }

    /**
     * Prints line spacers
     */
    public static void printLineSpacer() {
        System.out.println("\t**************************************************");
    }

    /**
     * Prints the list of entries in the task list that matches the user's query
     *
     * @param matches List of entries in task list that matches with the user's query
     */
    public static void printFoundEntries(List<Task> matches) {
        int numOfMatches = matches.size();
        if (numOfMatches == 0) {
            System.out.println("\tSorry, there are no matches");
        } else {
            System.out.println("\tHere are your matching entries:");
            for (int i = 0; i < numOfMatches; i++) {
                System.out.println("\t" + (i + 1) + "." + matches.get(i).toString());
            }
        }
    }
}
