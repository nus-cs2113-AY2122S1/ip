package duke.ui;

import duke.task.Task;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.constants.DukeConstants.INDENT;
import static duke.constants.DukeConstants.STRAIGHT_LINE;
import static duke.constants.DukeConstants.WHITESPACE;
import static duke.constants.DukeDataStorageConstants.FILE_DATE_TIME_WRONG_FORMAT_MESSAGE;
import static duke.constants.DukeDataStorageConstants.FILE_WRONG_FORMAT_MESSAGE;
import static duke.constants.DukeDataStorageConstants.INVALID_FILE_TYPE_MESSAGE;
import static duke.constants.DukeOutputMessages.BYE_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.BYE_MESSAGE;
import static duke.constants.DukeOutputMessages.COMMAND_WRONG_FORMAT_MESSAGE;
import static duke.constants.DukeOutputMessages.DATE_TIME_INVALID_MESSAGE;
import static duke.constants.DukeOutputMessages.DEADLINE_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.DELETE_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.DONE_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.DUKE_LOGO;
import static duke.constants.DukeOutputMessages.ENTER_HELP;
import static duke.constants.DukeOutputMessages.EVENT_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.FIND_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.HELLO_FROM;
import static duke.constants.DukeOutputMessages.HELP_INTRO_MESSAGE;
import static duke.constants.DukeOutputMessages.HERE_ARE_TASKS_CONTAINING_MESSAGE;
import static duke.constants.DukeOutputMessages.HERE_IS_TASK_LIST_MESSAGE;
import static duke.constants.DukeOutputMessages.HEY_MESSAGE;
import static duke.constants.DukeOutputMessages.LIST_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.QUERY_NOT_FOUND_MESSAGE;
import static duke.constants.DukeOutputMessages.TASK_ADDED_MESSAGE;
import static duke.constants.DukeOutputMessages.TASK_DELETED_MESSAGE;
import static duke.constants.DukeOutputMessages.TASK_LIST_EMPTY_MESSAGE;
import static duke.constants.DukeOutputMessages.TASK_MARKED_DONE_MESSAGE;
import static duke.constants.DukeOutputMessages.TASK_NOT_IN_LIST_MESSAGE;
import static duke.constants.DukeOutputMessages.TODO_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.UNRECOGNIZED_COMMAND_MESSAGE;

/**
 * Handles interaction with the user. Such interaction includes reading input
 * from the user and printing output and error messages for the user.
 */
public class Ui {

    /** Offset to  differentiate between 0-based and 1-based indexing */
    private static final int DISPLAYED_INDEX_OFFSET = 1;

    /** Format of task to be printed in list */
    private static final String DISPLAYED_TASK_FORMAT = " %1$d. %2$s";

    private static final Scanner in = new Scanner(System.in);
    private static final PrintStream out = System.out;

    public static String readUserInput() {
        return in.nextLine();
    }

    /**
     * Prints greeting to the user upon entry of the program along with a logo.
     */
    public static void printHeyMessage() {
        out.println(HELLO_FROM + DUKE_LOGO);
        out.println(STRAIGHT_LINE);
        out.println(HEY_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a numbered list of tasks when {@code list} command is entered by the user.
     *
     * @param taskList list of tasks to be printed
     */
    public static void printTaskList(ArrayList<Task> taskList) {
        out.println(STRAIGHT_LINE);
        out.println(HERE_IS_TASK_LIST_MESSAGE);
        for (int i = 0; i < taskList.size(); i++) {
            String formattedTask = formatTaskToBeDisplayed(i + DISPLAYED_INDEX_OFFSET, taskList.get(i));
            out.println(formattedTask);
        }
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a numbered list of tasks containing {@code query} when {@code find} command is entered by the user.
     *
     * @param taskListContainingQuery list of tasks containing {@code query}
     * @param query keyword searched for by user using the {@code find} command
     */
    public static void printTaskListContainingQuery(ArrayList<Task> taskListContainingQuery, String query) {
        out.println(STRAIGHT_LINE);
        out.println(HERE_ARE_TASKS_CONTAINING_MESSAGE + "\"" + query + "\"");
        for (int i = 0; i < taskListContainingQuery.size(); i++) {
            String formattedTask = formatTaskToBeDisplayed(i + DISPLAYED_INDEX_OFFSET, taskListContainingQuery.get(i));
            out.println(formattedTask);
        }
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user about a task that has been successfully marked done along with the task.
     *
     * @param doneTask {@code Task} which has been marked done
     */
    public static void printTaskMarkedDoneMessage(Task doneTask) {
        out.println(STRAIGHT_LINE);
        out.println(TASK_MARKED_DONE_MESSAGE);
        out.println(INDENT + doneTask);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that a task has been added successfully along with the task, and number of
     * tasks currently in the list.
     *
     * @param addedTask {@code Task} which was added successfully
     * @param taskListSize size of {@code TaskList} after addition of the new task to inform user of the current
     *                     number of tasks in the list
     */
    public static void printTaskAddedMessage(Task addedTask, int taskListSize) {
        out.println(STRAIGHT_LINE);
        out.println(TASK_ADDED_MESSAGE);
        out.println(INDENT + addedTask);
        out.println(WHITESPACE + "You now have " + taskListSize + " task(s) in the list.");
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that a task has been deleted successfully along with the task, and number of
     * tasks left in the list.
     *
     * @param deletedTask {@code Task} which was deleted successfully
     * @param taskListSize size of {@code TaskList} after deletion of the task to inform user of the current
     *                     number of tasks in the list
     */
    public static void printTaskDeletedMessage(Task deletedTask, int taskListSize) {
        out.println(STRAIGHT_LINE);
        out.println(TASK_DELETED_MESSAGE);
        out.println(INDENT + deletedTask);
        out.println(WHITESPACE + "You now have " + (taskListSize - 1) + " task(s) in the list." );
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that the {@code TaskList} is empty.
     */
    public static void printEmptyListMessage() {
        out.println(STRAIGHT_LINE);
        out.println(TASK_LIST_EMPTY_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that the command entered is of the wrong format.
     * Prints a message suggesting the user to enter the {@code help} command for help.
     */
    public static void printInvalidCommandFormatMessage() {
        out.println(STRAIGHT_LINE);
        out.println(COMMAND_WRONG_FORMAT_MESSAGE);
        out.println(ENTER_HELP);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that the task they are trying to mark done or delete is not in the
     * list.
     */
    public static void printTaskNotInListMessage() {
        out.println(STRAIGHT_LINE);
        out.println(TASK_NOT_IN_LIST_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a list of commands and their correct formats to aid the user in usage of Duke.
     */
    public static void printHelp() {
        out.println(STRAIGHT_LINE);
        out.println(HELP_INTRO_MESSAGE);
        out.println(LIST_COMMAND_FORMAT);
        out.println(DONE_COMMAND_FORMAT);
        out.println(TODO_COMMAND_FORMAT);
        out.println(DEADLINE_COMMAND_FORMAT);
        out.println(EVENT_COMMAND_FORMAT);
        out.println(FIND_COMMAND_FORMAT);
        out.println(DELETE_COMMAND_FORMAT);
        out.println(BYE_COMMAND_FORMAT);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that the keyword being searched for with {@code find} could not be
     * found in the list of tasks.
     */
    public static void printQueryNotFoundMessage() {
        out.println(STRAIGHT_LINE);
        out.println(QUERY_NOT_FOUND_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that data in the storage file is of the wrong format.
     */
    public static void printFileTaskInvalidFormatMessage() {
        out.println(STRAIGHT_LINE);
        out.println(FILE_WRONG_FORMAT_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that the type of file used as a storage file is invalid.
     * A valid file ends with {@code ".txt"}.
     */
    public static void printInvalidFileTypeMessage() {
        out.println(STRAIGHT_LINE);
        out.println(INVALID_FILE_TYPE_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that date and time read from storage file is invalid or of the wrong format
     */
    public static void printFileInvalidDateTimeMessage() {
        out.println(STRAIGHT_LINE);
        out.println(FILE_DATE_TIME_WRONG_FORMAT_MESSAGE);
        out.println(ENTER_HELP);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that date and time entered is invalid or of the wrong format.
     */
    public static void printInvalidDateTimeMessage() {
        out.println(STRAIGHT_LINE);
        out.println(DATE_TIME_INVALID_MESSAGE);
        out.println(ENTER_HELP);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a message informing the user that an unrecognized command has been entered.
     * Commands recognized can be found in the {@code CommandWord} enumeration.
     */
    public static void printUnrecognizedCommandMessage() {
        out.println(STRAIGHT_LINE);
        out.println(UNRECOGNIZED_COMMAND_MESSAGE);
        out.println(ENTER_HELP);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Prints a greeting message for the user before the program exits.
     */
    public static void printByeMessage() {
        out.println(STRAIGHT_LINE);
        out.println(BYE_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    /**
     * Formats a {@code Task} object into a {@code String} which is printed when listing the task
     *
     * @param displayedIndex index of {@code Task} in the printed list
     * @param task {@code Task} to be printed
     * @return formatted {@code String} which appends {@code task} to a number
     */
    private static String formatTaskToBeDisplayed(int displayedIndex, Task task) {
        return String.format(DISPLAYED_TASK_FORMAT, displayedIndex, task.toString());
    }
}
