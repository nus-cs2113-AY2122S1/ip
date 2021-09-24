package duke.ui;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String getCommand() {
        return in.nextLine();
    }

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

    public static void printList(ArrayList<Task> tasks) {
        // Print out the whole task list with their status icons
        printSign();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.print(i + ".");
            System.out.println(tasks.get(i - 1).toString());
        }
        printSign();
    }

    public static void printTotalNumOfTasks(ArrayList<Task> tasks) {
        // Print out the total number of the tasks and what is added to do
        printSign();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printSign();
    }

    public static void exit() {
        // Exit the program one user key in "bye"
        printSign();
        System.out.println("Bye. Hope to see you again soon!\n");
        printSign();
    }

    public static void printSign() {
        // Print out a "-" line
        for (int i = 1; i <= 40; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printInvalidMessage() {
        printSign();
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printSign();
    }
}
