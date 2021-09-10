package duke.output;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class OutputHandler {

    public static final String NUMBER_LIST_SEPARATOR = ". ";
    public static final String TASK_PADDING = "   ";
    public static final String NEWLINE = "\n";
    public static final String HELP_SEPARATOR = " - ";
    
    /**
     * Print a welcome message when starting the program.
     */
    public void printWelcomeMessage() {
        System.out.println(NEWLINE + "Hello! I'm Duke, your personal task assistant.");
        System.out.println("Get started by adding a task, or type help to see what I can do." + NEWLINE);
    }

    /**
     * Print a message when the task number given is out of bounds.
     */
    public void printTaskNumberOutOfBoundsMessage() {
        System.out.println(NEWLINE + "Oops, I can't find that task in the list!" + NEWLINE);
    }

    /**
     * Print a message when the task number given is invalid or not a number.
     */
    public void printInvalidTaskNumberMessage() {
        System.out.println(NEWLINE + "Oops, that is not a valid task number!" + NEWLINE);
    }

    /**
     * Print a message when attempting to mark an already completed task as done.
     */
    public void printTaskDoneMessage() {
        System.out.println(NEWLINE + "This task has been completed!" + NEWLINE);
    }

    /**
     * Print a message when no task name is given.
     */
    public void printNoTaskNameMessage() {
        System.out.println(NEWLINE + "Please tell me the name of the task." + NEWLINE);
    }

    /**
     * Print a message when no task name or due date is given for a deadline.
     */
    public void printNoDeadlineMessage() {
        System.out.println(NEWLINE + "Please tell me what the task is and when it is due." + NEWLINE);
    }

    /**
     * Print an error message when no event name or date is given.
     */
    public void printNoEventMessage() {
        System.out.println(NEWLINE + "Please tell me what the event is and when it is happening." + NEWLINE);
    }

    /**
     * Prints a message greeting the user.
     */
    public void printGreetingMessage() {
        System.out.println(NEWLINE + "Hello! What can I do for you?" + NEWLINE);
    }

    /**
     * Print a message when exiting the program.
     */
    public void printExitMessage() {
        System.out.println(NEWLINE + "Bye. Hope to see you again soon!" + NEWLINE);
    }

    /**
     * Print a message when input matches none of the commands.
     */
    public void printInvalidCommandMessage() {
        System.out.println(NEWLINE + "I'm not sure what you want to do.");
        System.out.println("Check if you've misspelled something!");
        System.out.println("Type help for a list of commands." + NEWLINE);
    }

    /**
     * Print an error message when the command type returned does not match any of the command types given.
     * (This should not happen.)
     */
    public void printUnknownErrorMessage() {
        System.out.println(NEWLINE + "Something went wrong!" + NEWLINE);
    }

    /**
     * Print a message when there are no tasks in a list.
     */
    public void printNoTasksInListMessage() {
        System.out.println(NEWLINE + "There are no tasks in the list!" + NEWLINE);
    }


    /**
     * Get the substring of a string with leading and trailing spaces removed.
     *
     * @param input         The input string
     * @param startingIndex Starting index of substring
     * @param endingIndex   Ending index of substring
     * @return The trimmed substring
     */
    public String getTrimmedSubstring(String input, int startingIndex, int endingIndex) {
        return input.substring(startingIndex, endingIndex).trim();
    }

    /**
     * Mark a task as done.
     *
     * @param taskNumber The task number to mark as done
     */
    public void markAsDone(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        System.out.println(NEWLINE + "Nice! I've marked the task as done:");
        System.out.println(TASK_PADDING + tasks.get(taskNumber) + NEWLINE);
    }

    /**
     * Print the task added and number of tasks in the list
     *
     * @param tasks The list of tasks
     */
    public void addTask(ArrayList<Task> tasks, Task task) {
        System.out.println(NEWLINE + "Got it. I've added this task:");
        System.out.println(TASK_PADDING + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list." + NEWLINE);
    }

    /**
     * Print a message when a task is deleted.
     *
     * @param tasks      The list of tasks
     * @param taskNumber The task number of the task to be deleted
     */
    public void deleteTask(ArrayList<Task> tasks, int taskNumber) {
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println(NEWLINE + "Noted. I deleted this task:");
        System.out.println(TASK_PADDING + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list." + NEWLINE);
    }

    /**
     * Print the tasks currently in the list.
     *
     * @param tasks The list of tasks
     */
    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println(NEWLINE + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + NUMBER_LIST_SEPARATOR + tasks.get(i));
        }
        System.out.print(NEWLINE);
    }

    /**
     * Print the help message, which lists all commands.
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
    }

    /**
     * Print a help message for a specific command.
     * @param command The command
     * @param description The command's description
     * @param format The usage format for the command
     */
    public void printCommandHelpMessage(String command, String description, String format, String example) {
        printCommandDescription(command, description);
        printCommandUsage(format);
        printCommandExample(example);
        System.out.println();
    }

    /**
     * Print a command description for a specific command.
     * @param command The command
     * @param description The command's description
     */
    public void printCommandDescription(String command, String description) {
        System.out.println(command + HELP_SEPARATOR + description);
    }

    /**
     * Print the usage format for a specific command.
     * @param format The usage format of the command
     */
    public void printCommandUsage(String format) {
        if (!format.equals("none")) {
            System.out.println("usage: " + format);
        }
    }

    /**
     * Print a usage example for a specific command
     * @param example An example for the command
     */
    public void printCommandExample(String example) {
        if (!example.equals("none")) {
            System.out.println("example: " + example);
        }
    }

    public void printFileNotFoundMessage() {
        System.out.println("You don't have any saved tasks yet!" + NEWLINE);
    }

    public void printFileSaveError(IOException e) {
        System.out.println("Something went wrong while saving tasks: " + e.getMessage());
    }
}
