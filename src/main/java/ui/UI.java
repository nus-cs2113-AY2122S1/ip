package ui;

import task.Task;

import java.util.ArrayList;
import java.util.Scanner;
public class UI {
    private static final String line = "____________________________________________________________";

    /**
     * Formats and prints strings to standard output
     *
     * @param response taro's response to be printed out
     */
    public void printString(String response) {
        System.out.println(line);
        System.out.println(" " + response);
        System.out.println(line);
    }

    /**
     * Prints taro logo to the standard output with a greeting
     */
    public void printLogo() {
        String logo = " _                       \n" +
                "| |_   ____   ____  ___  \n" +
                "|  _) / _  | / ___)/ _ \\ \n" +
                "| |__( ( | || |   | |_| |\n" +
                " \\___)\\_||_||_|    \\___/ ";
        System.out.println("greetings from\n" + logo);
    }

    /**
     * Prints a greeting to standard output
     */
    public void greetUser() {
        printString("hello! I'm taro\n" +
                " how can I help you?");
    }

    /**
     * Reads in user input
     *
     * @return a String containing the raw user input
     */
    public String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Acknowledge that a task has been added to the list by printing out the task details
     *
     * @param task the task.Task object that has been added to the list
     * @param taskCount the total number of tasks stored by taro
     */
    public void printTaskAddedMessage(Task task, int taskCount) {
        System.out.println(line);
        System.out.println(" okay! I have added this task:");
        System.out.println("  " + task);
        System.out.println(" now you have " + taskCount + " tasks in the list");
        System.out.println(line);
    }

    /**
     * Prints out a list of all tasks stored by taro, with an indication of whether the task is a todo item, a
     * deadline or an event and if it has been completed or not
     *
     * @param tasks the list of tasks stored by taro
     * @param taskCount the total number of tasks on the list
     */
    public void printTasksList(ArrayList<Task> tasks, int taskCount) {
        System.out.println(line);
        if (taskCount > 0) {
            System.out.println(" here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
            }
        } else {
            System.out.println(" there are no tasks on your list :)");
        }
        System.out.println(line);
    }

    /**
     * Print a response when a task has been marked as complete
     *
     * @param task the task.Task item that has been marked done
     */
    public void printMarkedDoneMessage(Task task) {
        printString("great work! I have marked this task as done:\n" +
                "  " + task);
    }

    /**
     * Prints a response when a task is deleted from internal storage indicating the description of the task deleted
     * and the number of tasks left on the list.
     *
     * @param deletedTask the Task object that was deleted
     * @param taskCount the total number of remaining tasks
     */
    public void printTaskDeletedMessage(Task deletedTask, int taskCount) {
        printString("noted. I've removed this task:\n" +
                "  " + deletedTask +
                "\n now you have " + taskCount + " tasks in the list.");
    }

    public void printExitMessage() {
        printString("bye. I hope to see you soon!");
    }

}
