package duke.components;

import duke.task.Task;
import java.util.Scanner;

import static duke.constants.Messages.HORIZONTAL_LINE;
import static duke.constants.Messages.LINE_PREFIX;
import static duke.constants.Messages.LOGO;
import static duke.constants.Messages.MESSAGE_ADDED_TASK;
import static duke.constants.Messages.MESSAGE_GOODBYE;
import static duke.constants.Messages.MESSAGE_GREETING;
import static duke.constants.Messages.MESSAGE_HAVING_ONE_TASK;
import static duke.constants.Messages.MESSAGE_HAVING_ZERO_TASK;
import static duke.constants.Messages.MESSAGE_MARK_TASK_DONE;
import static duke.constants.Messages.MESSAGE_REMOVED_TASK;

/**
 * A class that deals with interactions with the user.
 */
public class Ui {

    /**
     * Reads the text entered by the user.
     *
     * @param in The Scanner to read user input.
     * @return line Full line entered by the user.
     */
    public static String getUserInput(Scanner in) {
        String line = in.nextLine();
        return line;
    }

    /**
     * Prints the divider HORIZONTAL_LINE.
     */
    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the greeting message to standard output.
     */
    public static void printGreeting() {
        printMessage(LOGO + MESSAGE_GREETING);
    }

    /**
     * Prints the goodbye message to standard output.
     */
    public static void printGoodBye() {
        printMessage(MESSAGE_GOODBYE);
    }

    /**
     * Prints confirm message after adding ToDo, Event, or Deadline.
     *
     * @param task The task to be added.
     * @param numberOfTasks Number of tasks after adding the given task.
     */
    public static void printConfirmAdd(Task task, int numberOfTasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(MESSAGE_ADDED_TASK);
        System.out.println(LINE_PREFIX + LINE_PREFIX + task);
        if (numberOfTasks == 1) {
            System.out.println(MESSAGE_HAVING_ONE_TASK);
        } else {
            System.out.println(LINE_PREFIX + "Now you have " + numberOfTasks + " tasks in the list.");
        }
    }

    /**
     * Prints confirm message after delete a task.
     *
     * @param task The task to be deleted.
     * @param numberOfTasksBeforeDelete Number of tasks before delete the given task.
     */
    public static void printConfirmDelete(Task task, int numberOfTasksBeforeDelete) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(MESSAGE_REMOVED_TASK);
        System.out.println(LINE_PREFIX + LINE_PREFIX + task);
        if (numberOfTasksBeforeDelete == 2) {
            System.out.println(MESSAGE_HAVING_ONE_TASK);
        } else if (numberOfTasksBeforeDelete == 1) {
            System.out.println(MESSAGE_HAVING_ZERO_TASK);
        } else {
            System.out.println(LINE_PREFIX + "Now you have " + (numberOfTasksBeforeDelete - 1) + " tasks in the list.");
        }
    }

    /**
     * Prints confirm message after marking a task as done.
     *
     * @param task The task to be marked as done.
     */
    public static void printConfirmMarkDone(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(MESSAGE_MARK_TASK_DONE);
        System.out.println(LINE_PREFIX + LINE_PREFIX + task);
    }

    /**
     * Prints message to the standard output.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }
}
