package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner inputScanner = new Scanner(System.in);
    private static final String spacer = "  ";

    /**
     * Gets user input and returns it as a String.
     *
     * @return the String containing the user input.
     */
    public String readInput() {
        String line;
        line = inputScanner.nextLine();
        return line;
    }

    /**
     * Prints a horizontal line in the terminal.
     */
    public void printLine() {
        System.out.println(spacer + "──────────────────────────────");
    }

    /**
     * Prints the application start message in the terminal.
     */
    public void printStartMessage() {
        printLine();
        System.out.println(spacer + "Hello! I'm Duke\n  How may I assist you");
        printLine();
    }

    /**
     * Prints the application exit message in the terminal.
     */
    public void printExitMessage() {
        printLine();
        System.out.println(spacer + "Goodbye! Hope to see you soon!");
        printLine();
    }

    /**
     * Prints the error message
     *
     * @param errorMessage The error message to be printed
     */
    public void printErrorMessage(String errorMessage) {
        printLine();
        System.out.println(spacer + errorMessage);
        printLine();
    }

    /**
     * Prints the help message with a list of all commands
     */
    public void printHelpMessage() {
        printLine();
        System.out.println(spacer + "Here is a list of commands:");
        System.out.println(spacer + spacer + "todo [task name] - adds todo task to task manager");
        System.out.println(spacer + spacer + "event [task name] /at [date] - adds event task to task manager");
        System.out.println(spacer + spacer + "deadline [task name] /by [date] - adds deadline task to task manager");
        System.out.println(spacer + spacer + "list - lists all tasks");
        System.out.println(spacer + spacer + "done [task index] - marks the specified task as completed");
        System.out.println(spacer + spacer + "delete [task index] - deletes the specified task");
        System.out.println(spacer + spacer + "bye - close the application");
        printLine();
    }

    /**
     * Prints a message saying that the given task has been added, as well as the current
     * total number of tasks
     *
     * @param task       The task that was added to the taskList
     * @param tasksCount The current total number of tasks in the taskList
     */
    public void printAddTaskMessage(Task task, int tasksCount) {
        printLine();
        System.out.println(spacer + "Ok! I've added this task:");
        System.out.println(spacer + spacer + task);
        System.out.println(spacer + "Now you have " + tasksCount + " tasks.");
        printLine();
    }

    /**
     * Prints a message saying that the specified task has been marked as completed
     *
     * @param task       The task that was added to the taskList
     */
    public void printCompleteTaskMessage(Task task) {
        printLine();
        System.out.println(spacer + "Ok! I've marked this task as done:");
        System.out.println(spacer + spacer + task);
        printLine();
    }

    /**
     * Prints a message saying that the specified task has been deleted, as well as
     * the current total number of tasks
     *
     * @param removedTask       The task that was added to the taskList
     * @param tasksCount The current total number of tasks in the taskList
     */
    public void printDeleteTaskMessage(Task removedTask, int tasksCount) {
        printLine();
        System.out.println(spacer + "Ok! I've deleted this task:");
        System.out.println(spacer + spacer + removedTask);
        System.out.println(spacer + "Now you have " + tasksCount + " tasks.");
        printLine();
    }

    /**
     * Prints a message listing all tasks in tasks
     *
     * @param tasks The ArrayList of tasks from the taskList class which contains all tasks
     */
    public void printAllTasks(ArrayList<Task> tasks) {
        printLine();
        System.out.println("  Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(spacer + spacer + (i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        printLine();
    }

    /**
     * Prints the Duke application logo in the terminal.
     */
    public void printAppLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
