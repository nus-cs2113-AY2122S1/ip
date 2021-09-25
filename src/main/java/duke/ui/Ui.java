package duke.ui;

import duke.task.Task;
import duke.task.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final int LINE_WIDTH = 60;

    /**
     * Scanner object to read user input.
     */
    private static final Scanner IN = new Scanner(System.in);

    /**
     * Prints a horizontal line for improving readability.
     */
    public void printHorizontalLine() {
        System.out.println("_".repeat(LINE_WIDTH));
    }

    /**
     * Prints message when a file error has occurred.
     */
    public void printFileError() {
        System.out.println("File error");
    }

    /**
     * Prints a greeting message to the user
     */
    public void printGreeting() {
        printHorizontalLine();
        String greeting = " Hello! I'm Duke" + System.lineSeparator() +
                " What can I do for you?";
        System.out.println(greeting);
        printHorizontalLine();
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints the details of the newly added task.
     *
     * @param task        Task object that was added.
     * @param taskManager TaskManager object used to get
     *                    task count.
     */
    public void printAddTask(Task task, TaskManager taskManager) {
        if (task == null) {
            return;
        }
        String message = " Got it. I've added this task:" + System.lineSeparator() +
                "  " + task + System.lineSeparator() + " Now you have " +
                taskManager.getTasksCount() + " tasks in the list.";
        System.out.println(message);
    }

    /**
     * Prints a warning message to the user informing the
     * user of the correct Task ID type.
     */
    public void printInvalidTaskNumberFormat() {
        String message = "  Task ID must be an integer!";
        System.out.println(message);
    }

    /**
     * Prints a message to the user informing that there
     * is no task corresponding to the given Task ID.
     */
    public void printInvalidTaskNumber() {
        String message = "  Task ID does not exist!";
        System.out.println(message);
    }

    /**
     * Prints a message containing details of the task
     * that was marked as done.
     *
     * @param task Task object that was marked as done.
     */
    public void printMarkAsDone(Task task) {
        String message = (" Nice! I've marked this task as done:" +
                System.lineSeparator() + "   " + task);
        System.out.println(message);
    }

    /**
     * Prints a message containing the details of the task
     * that was deleted.
     *
     * @param task        Task object that was deleted.
     * @param taskManager TaskManager object used to get the
     *                    task count.
     */
    public void printDeleteTask(Task task, TaskManager taskManager) {
        String message = " Got it! I've removed this task:" +
                System.lineSeparator() + "   " + task +
                System.lineSeparator() + " Now you have " +
                taskManager.getTasksCount() + " tasks in the list.";
        System.out.println(message);
    }

    /**
     * Prints all the current tasks.
     *
     * @param taskManager TaskManager object used to list
     *                    all the current tasks.
     */
    public void listTasks(TaskManager taskManager) {
        System.out.println(" Here are the tasks in your list:");
        taskManager.listTasks();
    }

    /**
     * Prints a farewell message to the user.
     */
    public void printFarewell() {
        String farewell = " Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    /**
     * Prints the correct command format for adding a
     * deadline task.
     */
    public void printDeadlineCommandFormat() {
        String message = ("  Invalid command format!!" + System.lineSeparator() +
                "  Correct format is: " + "deadline <task name> /by <date_time>");
        System.out.println(message);
    }

    /**
     * Prints a warning message informing the user that
     * a description is required from them.
     *
     * @param command The corresponding command which the
     *                user has attempted to use.
     */
    public void printEmptyDescription(String command) {
        String emptyDescription = " ☹ OOPS!!! The description of a " +
                command + " cannot be empty.";
        System.out.println(emptyDescription);
    }

    /**
     * Prints the correct command format for adding
     * an event task.
     */
    public void printEventCommandFormat() {
        String message = ("  Invalid command format!!" + System.lineSeparator() +
                "  Correct format is: " + "event <event name> /at <date_time>");
        System.out.println(message);
    }

    /**
     * Depending on which command (deadline or event) the user has
     * incorrectly used, call the corresponding method to print the
     * correct command format.
     *
     * @param command The command which the user has incorrectly used.
     */
    public void printCorrectCommandFormat(String command) {
        switch (command) {
        case COMMAND_DEADLINE:
            printDeadlineCommandFormat();
            break;
        case COMMAND_EVENT:
            printEventCommandFormat();
            break;
        default:
            return;
        }
    }

    /**
     * Reads the user's inputs (commands).
     *
     * @return The user's inputs as a String.
     */
    public String readCommand() {
        return IN.nextLine();
    }

    /**
     * Prints message when Duke is unable to understand a command.
     */
    public void printInvalidCommand() {
        String invalid = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(invalid);
    }

    /**
     * Prints a message informing that the current tasks have been
     * saved successfully and the file location where the tasks have
     * been saved to.
     *
     * @param filePath File path of the file where the tasks have
     *                 been saved to.
     */
    public void printSuccessfullySavedTasks(String filePath) {
        printHorizontalLine();
        System.out.println("Current tasks successfully saved at: " +
                System.lineSeparator() + filePath);
        printHorizontalLine();
    }

    /**
     * Prints a message informing that there were no tasks
     * that contain the keyword provided by the user.
     */
    private void printNoMatchingResults() {
        String message = "  Sorry, no matching results found!";
        System.out.println(message);
    }

    /**
     * Prints all the matching tasks (tasks that contain the keyword
     * provided by the user).
     *
     * @param matchingTasks ArrayList containing all the matching tasks.
     */
    public void printMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.size() == 0) {
            printNoMatchingResults();
            return;
        }
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 1; i <= matchingTasks.size(); i++) {
            System.out.println(" " + i + "." +
                    matchingTasks.get(i - 1).toString());
        }
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
     */
    public void println(String message) {
        System.out.println(message);
    }
}
