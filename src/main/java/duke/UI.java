package duke;

import java.util.Scanner;

import java.util.List;

import duke.task.*;

/**
 * Prints the outputs corresponding to different actions.
 */
public class UI {

    /**
     * Prints the dot line breaker.
     */
    public static void printBreaker() {
        System.out.println("......................................................................");
    }

    /**
     * Prints welcome message after starting the bot.
     */
    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printBreaker();
        System.out.println("Hi! I'm Duke.\n" + "How can I make your life easier?");
        printBreaker();
    }

    /**
     * Prints summary message after a task is marked as done.
     *
     * @param taskDone The string description of the task done.
     */
    public static void printDoneTask(String taskDone) {
        printBreaker();
        System.out.println("Wonderful! This task is now marked as done:\n");
        System.out.println(taskDone);

    }

    /**
     * Prints summary message after a task is searched for.
     *
     * @param taskSearch The List of task searched for.
     */
    public static void printSearchTask(List<Task> taskSearch) {
        if (taskSearch.size() == 0) {
            System.out.println("OPPS!!! No matches found:(");
        } else {
            System.out.println("Here is the task that you are looking for:)");
            for (int i = 0; i < taskSearch.size(); i++) {
                System.out.println((i + 1) + "." + taskSearch.get(i).toString());
            }
        }
    }

    /**
     * Prints summary message after a task is added.
     *
     * @param taskNumber The integer total number of tasks in the list.
     * @param taskRecord The task added.
     */
    public static void printRecordTask(int taskNumber, Task taskRecord) {
        printBreaker();
        System.out.println("Got it. I've added this task:\n");
        System.out.println(taskRecord.toString());
        System.out.println("Now you have " + taskNumber + " tasks in your list");
    }

    /**
     * Prints summary message after a task is deleted from the list.
     *
     * @param taskNumber The integer total number of tasks in the list.
     * @param taskDelete The string description of the task deleted.
     */
    public static void printDeleteTask(int taskNumber, String taskDelete) {
        printBreaker();
        System.out.println("Alright, the following task has been removed");
        System.out.println(taskDelete);
        System.out.println("Now you have " + (taskNumber - 1) + " tasks left");
    }

    /**
     * Prints bye message when terminating the bot.
     */
    public static void printByeMessage() {
        System.out.println("Bye bye! Have a wonderful day!");
        printBreaker();
    }
}