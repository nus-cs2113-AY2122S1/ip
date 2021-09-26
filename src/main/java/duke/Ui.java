package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class Ui {
    public static final String LINE_SEPARATOR = "_____________________________";

    /**
     * Prints welcome message
     *
     */
    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        // greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints farewell message
     *
     */
    public static void printFarewellMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the message containing how many tasks are in the list
     *
     * @param size the size of the list, how many tasks are in the list
     */
    public static void printTaskNumberMessage(int size) {
        System.out.println("Now you have " + size + " tasks in the list");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the total number of tasks undone
     *
     * @param undoneTasks the total number of tasks not marked as done
     */
    public static void printUndoneTaskMessage(int undoneTasks) {
        System.out.println("Total tasks undone: " + undoneTasks);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the message when a user marks a task as done
     *
     * @param taskList the list of tasks the user has
     * @param doneTaskNumber the index of the task to be marked as done
     * @param numberOfTasksUndone the total number of tasks not marked as done
     */
    public static void printMarkAsDoneMessage(ArrayList<Task> taskList, int doneTaskNumber, int numberOfTasksUndone) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Good job! This task is marked as done:");
        System.out.println(taskList.get(doneTaskNumber).toString());
        System.out.println("Now you have " + numberOfTasksUndone + " tasks undone");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the message after a task is being added
     *
     * @param args the description of the task to be printed
     */
    public static void printAddedTaskMessage(String args) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("added: " + args);
    }

    /**
     * Prints the message after a task is deleted
     *
     * @param taskList the list of tasks the user has
     * @param taskIndex the index of the task to be deleted
     */
    public static void printDeletedTaskMessage(ArrayList<Task> taskList, int taskIndex) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it! This task was removed:" + System.lineSeparator()
                + taskList.get(taskIndex).toString());
    }
}
