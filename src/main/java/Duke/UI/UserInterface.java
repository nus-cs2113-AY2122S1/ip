package Duke.UI;

import Duke.Exception.DukeException;
import Duke.TaskTypes.Task;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import static Duke.UI.DukeConstants.*;

/**
 * Class which stores all the command line printing methods
 */
public class UserInterface {

    /**
     * Print Duke welcome message
     */
    public static void printDukeGreet() {
        System.out.println("Hello from\n" + DUKE_LOGO);
        System.out.println(LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Print user manual for duke program
     */
    public static void printDukeHelp() {
        System.out.println(LINE);
        System.out.println(HELP_MESSAGE);
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * prints all the tasks in the user's list
     * @param taskList the list which stores user's tasks
     */
    public static void printList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            DukeException.emptyTaskException();
            return;
        }

        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * print which task is marked as done
     *
     * @param taskDoneIndex the index of the task to be marked done
     * @param taskList the list which stores user's tasks
     */
    public static void taskDoneMessage (int taskDoneIndex, ArrayList<Task> taskList) {
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskList.get(taskDoneIndex - 1).toString());
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * print which task is added to the task list
     *
     * @param newItem task to be added
     * @param taskList the list which stores user's tasks
     */
    public static void taskAddedMessage (Task newItem, ArrayList<Task> taskList) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * print which task is deleted from the task list
     *
     * @param deletedTask task to be deleted
     * @param taskList the list which stores user's tasks
     */
    public static void deleteMessage(Task deletedTask, ArrayList<Task> taskList) {
        System.out.println(LINE);
        System.out.println(" Noted! I've removed this task:");
        System.out.println("   " + deletedTask.toString());
        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * prints an error message for exceptions in Duke Program
     */
    public static void generalDukeException() {
        System.out.println(LINE);
        System.out.println("Please input a valid command!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * prints goodbye message when Duke Program terminates
     */
    public static void printDukeExit() {
        System.out.println(LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE);
    }

    /**
     * Prints error message when todo instruction has no description
     */
    public static void emptyTodoMessage() {
        System.out.println(LINE);
        System.out.println("Your todo command does not have an argument!");
        System.out.println("To input a valid todo command, type \"todo (argument)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints error message when deadline instruction is invalid
     */
    public static void invalidDeadlineMessage() {
        System.out.println(LINE);
        System.out.println("Your deadline command is invalid!");
        System.out.println("To input a valid deadline command, type \"deadline (description) /by (deadline)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints error message when event instruction is invalid
     */
    public static void invalidEventMessage() {
        System.out.println(LINE);
        System.out.println("Your event command is invalid!");
        System.out.println("To input a event command, type \"event (description) /at (when)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints error message when done instruction is invalid
     */
    public static void invalidDoneMessage() {
        System.out.println(LINE);
        System.out.println("Your done command is invalid!");
        System.out.println("To input a done command, type \"done (task index)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints error message when delete instruction is invalid
     */
    public static void invalidDeleteMessage() {
        System.out.println(LINE);
        System.out.println("Your delete command is invalid!");
        System.out.println("To input a delete command, type \"delete (task index)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints message when task list is still empty
     */
    public static void printEmptyTaskMessage() {
        System.out.println(LINE);
        System.out.println(" No Tasks here yet. Go include some tasks!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints error message if save file is invalid
     */
    public static void printInvalidSaveFileMessage() {
        System.out.println(LINE);
        System.out.println("Your save file is invalid!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * prints error message if IO exception occurs when creating new file
     * @param ioException the exception which occurs from running program
     */
    public static void createIOExceptionMessage(IOException ioException) {
        System.out.println(LINE);
        System.out.println("Something went wrong: " + ioException.getMessage());
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * prints error message if IO exception occurs when saving file
     * @param ioException the exception which occurs from running program
     */
    public static void printSaveIOExceptionMessage(IOException ioException) {
        System.out.println(LINE);
        System.out.println("Something went wrong: " + ioException.getMessage());
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints error message when find instruction is invalid
     */
    public static void printInvalidFindMessage() {
        System.out.println(LINE);
        System.out.println("Your find command is invalid!");
        System.out.println("To input a find command, type \"find {searched word}\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints message when user query gives out no match
     */
    public static void printNoMatchMessage() {
        System.out.println(LINE);
        System.out.println("There are no matching tasks in your list!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints all the matched task from user query
     *
     * @param searchedWord the user query
     * @param tasksWithQuery the list of matched tasks with query
     */
    public static void printAllMatchingTasks(String searchedWord, ArrayList<Task> tasksWithQuery) {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list to \"" + searchedWord + "\"");
        for (int i = 0; i < tasksWithQuery.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasksWithQuery.get(i).toString());
        }
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints exception message if date-time format is invalid
     * @param dtpException the exception which occurs from running duke program
     */
    public static void createDtpExceptionMessage(DateTimeParseException dtpException) {
        System.out.println(LINE);
        System.out.println("Something went wrong: " + dtpException.getMessage());
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }
}
