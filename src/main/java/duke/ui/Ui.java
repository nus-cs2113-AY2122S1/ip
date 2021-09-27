package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

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

    /**
     * The function prints the recently added task
     */
    public static void printAddedTask(Task task, ArrayList<Task> tasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getDescription());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * The function prints the recently added task
     */
    public static void printDeletedTask(Task task, ArrayList<Task> tasks) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.getDescription());
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        printLine();
    }

    /**
     * Prints all the tasks
     *
     * @param tasks the array of tasks
     */
    public static void printTasks(ArrayList<Task> tasks) {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getDescription());
        }
        if (tasks.size() == 0) {
            System.out.println("Smartass, you need to add tasks before listing them !!!");
        }
        printLine();
    }
}
