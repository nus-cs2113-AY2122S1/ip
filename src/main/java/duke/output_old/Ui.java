package duke.output_old;

import duke.fileio_old.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static final String NUMBER_LIST_SEPARATOR = ". ";
    public static final String TASK_PADDING = "   ";
    public static final String NEWLINE = "\n";
    public static final String HELP_SEPARATOR = " - ";

    private Scanner in;

    public Ui(){
        in = new Scanner(System.in);
    }

    /**
     * Gets input when the user inputs a newline.
     * @return user input
     */
    public String getInput(){
        return in.nextLine();
    }

    /**
     * Prints a message when the program starts.
     */
    public void printWelcomeMessage() {
        System.out.println(NEWLINE + "Hello! I'm Duke, your personal task assistant." + NEWLINE +
                "Get started by adding a task, or type help to see what I can do." + NEWLINE);
    }

    /**
     * Prints a message when the task number given is out of bounds.
     */
    public void printTaskNumberOutOfBoundsMessage() {
        System.out.println(NEWLINE + "Oops, I can't find that task in the list!" + NEWLINE);
    }

    /**
     * Prints a message when the format of the task number is incorrect
     */
    public void printInvalidTaskNumberMessage() {
        System.out.println(NEWLINE + "Oops, that is not a valid task number!" + NEWLINE);
    }

    /**
     * Prints a message when a task is already completed.
     */
    public void printTaskDoneMessage() {
        System.out.println(NEWLINE + "This task has been completed!" + NEWLINE);
    }

    /**
     * Prints a message if the task description is not given.
     */
    public void printNoTaskNameMessage() {
        System.out.println(NEWLINE + "Please tell me the name of the task." + NEWLINE);
    }

    /**
     * Prints a message if the date for a deadline is not given.
     */
    public void printNoDeadlineMessage() {
        System.out.println(NEWLINE + "Please tell me what the task is and when it is due." + NEWLINE);
    }

    /**
     * Prints a message if the date for an event is not given.
     */
    public void printNoEventMessage() {
        System.out.println(NEWLINE + "Please tell me what the event is and when it is happening." + NEWLINE);
    }

    /**
     * Prints a message when the user types "hello".
     */
    public void printGreetingMessage() {
        System.out.println(NEWLINE + "Hello! What can I do for you?" + NEWLINE);
    }

    /**
     * Prints a message when the user exits the program.
     */
    public void printExitMessage() {
        System.out.println(NEWLINE + "Bye. Hope to see you again soon!" + NEWLINE);
    }

    /**
     * Prints a message when the user input matches none of the commands.
     */
    public void printInvalidCommandMessage() {
        System.out.println(NEWLINE + "I'm not sure what you want to do.");
        System.out.println("Check if you've misspelled something!");
        System.out.println("Type help for a list of commands." + NEWLINE);
    }

    /**
     * Prints a message when command parsed does not match any of the commands. (This should not occur.)
     */
    public void printUnknownErrorMessage() {
        System.out.println(NEWLINE + "Something went wrong!" + NEWLINE);
    }

    /**
     * Prints a message when the task list is empty.
     */
    public void printNoTasksInListMessage() {
        System.out.println(NEWLINE + "There are no tasks in the list!" + NEWLINE);
    }

    /**
     * Removes leading and trailing whitespaces of a substring.
     * @param input the string to be trimmed
     * @param startingIndex starting index of substring
     * @param endingIndex ending index of substring
     * @return the trimmed substring
     */
    public String getTrimmedSubstring(String input, int startingIndex, int endingIndex) {
        return input.substring(startingIndex, endingIndex).trim();
    }

    /**
     * Prints a message when a task is marked as done.
     * @param tasks list of tasks
     * @param taskNumber task number of task to be marked as done
     * @param storage file editor
     */
    public void printMarkedAsDone(ArrayList<Task> tasks, int taskNumber, Storage storage) {
        System.out.println(NEWLINE + "Nice! I've marked the task as done:");
        System.out.println(TASK_PADDING + tasks.get(taskNumber) + NEWLINE);
    }

    /**
     * Prints a message when a task is added.
     * @param tasks list of tasks
     * @param task task added to the list
     */
    public void printAddedTask(ArrayList<Task> tasks, Task task) {
        System.out.println(NEWLINE + "Got it. I've added this task:");
        System.out.println(TASK_PADDING + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list." + NEWLINE);
    }

    /**
     * Prints a message when a task is deleted
     * @param tasks list of tasks
     * @param task task deleted from the list
     */
    public void printDeletedTask(ArrayList<Task> tasks, Task task) {
        System.out.println(NEWLINE + "Noted. I deleted this task:");
        System.out.println(TASK_PADDING + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list." + NEWLINE);
    }

    /**
     * Prints the list of tasks, as well as their type and status.
     * @param tasks list of tasks
     */
    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println(NEWLINE + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + NUMBER_LIST_SEPARATOR + tasks.get(i));
        }
        System.out.print(NEWLINE);
    }

    /**
     * Prints a list of commands.
     */
    public void printHelpMessage() {
        System.out.println(NEWLINE + "Here are a list of commands:" + NEWLINE);
        printCommandHelpMessage("list", "lists all tasks", "none", "none");
        printCommandHelpMessage("todo", "adds a new to-do task", "todo [task description]",
                "todo organise my desk");
        printCommandHelpMessage("deadline", "adds a new task with a due date",
                "deadline [task description] /by [due date]", "deadline English assignment /by Wed 5pm");
        printCommandHelpMessage("event", "adds a new event",
                "event [task description] /at [event date]", "event meeting /at Thurs 9am");
        printCommandHelpMessage("done", "marks a task as done", "done [task number]",
                "done 2");
        printCommandHelpMessage("delete", "deletes a task from the list",
                "delete [task number]", "delete 3");
        printCommandHelpMessage("help", "lists all commands", "none", "none");
        printCommandHelpMessage("exit, bye, quit", "exits the program", "none", "none");
    }

    /**
     * Prints a help message for a command.
     * @param command the command
     * @param description description of the command
     * @param format usage format of the command
     * @param example example usage of the command
     */
    public void printCommandHelpMessage(String command, String description, String format, String example) {
        printCommandDescription(command, description);
        printCommandUsage(format);
        printCommandExample(example);
        System.out.println();
    }

    /**
     * Prints the description of a command.
     * @param command the command
     * @param description description of the command
     */
    public void printCommandDescription(String command, String description) {
        System.out.println(command + HELP_SEPARATOR + description);
    }

    /**
     * Prints the format for a command
     * @param format usage format for the command
     */
    public void printCommandUsage(String format) {
        if (!format.equals("none")) {
            System.out.println("usage: " + format);
        }
    }

    /**
     * Prints an example usage for a command.
     * @param example example usage for the command
     */
    public void printCommandExample(String example) {
        if (!example.equals("none")) {
            System.out.println("example: " + example);
        }
    }

    /**
     * Prints a message when attempting to get tasks from a file that does not exist.
     */
    public void printFileNotFoundMessage() {
        System.out.println("You don't have any saved tasks!" + NEWLINE);
    }

    /**
     * Prints a message when an IOException is thrown while saving the task list to a file.
     * @param e the IOException thrown
     */
    public void printFileSaveError(IOException e) {
        System.out.println("Something went wrong while saving tasks: " + e.getMessage());
    }
}
