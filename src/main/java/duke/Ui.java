package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    private static final String INDENT = "   ";

    /**
     * Print confirmation message to user that task has been added successfully to task list
     *
     * @param task Task that has been added to task list
     */
    public static void printTaskAddedMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + task);
        System.out.printf("Now you have %d tasks in the list.", TaskList.getTasklistSize());
        System.out.println();
    }

    /**
     * Print confirmation message to user that task has been removed successfully from task list
     *
     * @param task Task that has been deleted from task list
     */
    public static void printTaskDeletedMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(INDENT + task);
        System.out.printf("Now you have %d tasks in the list", TaskList.getTasklistSize());
        System.out.println();
    }

    /**
     * Print confirmation message to user that task has been marked as done
     *
     * @param task Task in task list that has been marked as done
     */
    public static void printTaskDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(INDENT + task);
    }


    /**
     * Prints list of Tasks in task list in a user-friendly format
     *
     * @param tasks Task list to be printed
     */
    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s", i + 1, task);
            System.out.println();
        }
    }

    /**
     * Prints greeting message to user on exit
     */
    public static void greetUserOnEnd() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints greeting message to user on startup
     */
    public static void greetUserOnStart() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
