package duke.Ui;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    public static final String HORIZONTAL_LINE = "_____________________________________________";

    /**
     * Prints welcome message
     */
    public static void printHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints bye message
     */
    public static void printBye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Print horizontal line for the borders
     */
    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Print task added message
     *
     * @param task  tasks added
     * @param tasks ArrayList of tasks
     */
    public static void gotItMessage(Task task, ArrayList<Task> tasks) {
        System.out.println("Got it!! I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Print all the filtered task
     *
     * @param filteredTask ArrayList of filtered task
     */
    public static void printFilteredList(ArrayList<Task> filteredTask){
        StringBuilder taskString = new StringBuilder();
        for (int i =0; i< filteredTask.size();i++){
            Task currentTask = filteredTask.get(i);
            taskString.append(i+1).append(".").append(currentTask.toString()).append("\n");
        }
        Ui.printHorizontalLine();
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(taskString.toString());
        Ui.printHorizontalLine();
    }

}
