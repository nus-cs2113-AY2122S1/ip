package duke.ui;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class used for interaction with users
 */
public class Ui {
    private Scanner in;

    /**
     * Constructor of the Class
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Returns user input as String
     *
     * @return String of user input
     */
    public String getCommand() {
        return in.nextLine();
    }

    /**
     * Prints DUKE logo and greeting message
     */
    public static void greet() {
        // the function is used to greet user in the very first beginning
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printSign();
        System.out.println("Hello! I'm duke.Duke\n");
        System.out.println("What can I do for you?\n");
        printSign();
    }

    /**
     * Prints out the whole task list with their status icons
     *
     * @param tasks ArrayList of tasks
     */
    public static void printList(ArrayList<Task> tasks) {
        printSign();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.print(i + ".");
            System.out.println(tasks.get(i - 1).toString());
        }
        printSign();
    }

    /**
     * Prints out the total number of the tasks and what is added to do
     *
     * @param tasks ArrayList of tasks
     */
    public static void printTotalNumOfTasks(ArrayList<Task> tasks) {
        printSign();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printSign();
    }

    /**
     * Exits the program once user key in "bye"
     */
    public static void exit() {
        printSign();
        System.out.println("Bye. Hope to see you again soon!\n");
        printSign();
    }

    /**
     * Prints out a divider line consist of "-"
     */
    public static void printSign() {
        for (int i = 1; i <= 40; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Prints out the message that indicates invalid input
     */
    public static void printInvalidMessage() {
        printSign();
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printSign();
    }
}
