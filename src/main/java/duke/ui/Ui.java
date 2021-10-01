package duke.ui;

import java.util.ArrayList;

import duke.task.Task;


public class Ui {

    public static final String SEPARATOR_STRING = ". ";
    private static final String WELCOME_GREETING = "Howdy there! I'm Fluke";
    private static final String WELCOME_ASK = "What can I do for you today master?";
    private static final String BYE_MESSAGE = "Bye. Hope to serve you again master!";

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
     * Prints the last added task
     */
    public static void printAddedTask(Task task, ArrayList<Task> tasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getDescription());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Prints the last deleted task
     */
    public static void printDeletedTask(Task task, ArrayList<Task> tasks) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.getDescription());
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        printLine();
    }

    /**
     * Prints all the tasks stored by Duke
     *
     * @param tasks array of tasks
     */
    public static void printTasks(ArrayList<Task> tasks) {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + SEPARATOR_STRING + tasks.get(i).getDescription());
        }
        if (tasks.size() == 0) {
            System.out.println("Smartass, you need to add tasks before listing them !!!");
        }
        printLine();
    }

    /**
     * Prints all the tasks matching the
     * string provided by the user, the
     * matching tasks are stored in filteredTasks
     *
     * @param filteredTasks array of tasks matching the user input
     */
    public static void printMatchingTasks(ArrayList<Task> filteredTasks) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : filteredTasks) {
            System.out.println(task.getDescription());
        }
        printLine();
    }
}
