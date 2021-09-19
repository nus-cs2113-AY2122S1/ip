package duke.ui;

import duke.task.Task;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.constants.DukeConstants.INDENT;
import static duke.constants.DukeConstants.STRAIGHT_LINE;
import static duke.constants.DukeConstants.WHITESPACE;
import static duke.constants.DukeDataStorageConstants.FILE_WRONG_FORMAT_MESSAGE;
import static duke.constants.DukeDataStorageConstants.INVALID_FILE_TYPE_MESSAGE;
import static duke.constants.DukeOutputMessages.BYE_MESSAGE;
import static duke.constants.DukeOutputMessages.COMMAND_WRONG_FORMAT_MESSAGE;
import static duke.constants.DukeOutputMessages.DEADLINE_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.DELETE_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.DONE_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.DUKE_LOGO;
import static duke.constants.DukeOutputMessages.ENTER_HELP;
import static duke.constants.DukeOutputMessages.EVENT_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.HELLO_FROM;
import static duke.constants.DukeOutputMessages.HELP_INTRO_MESSAGE;
import static duke.constants.DukeOutputMessages.HERE_IS_TASK_LIST_MESSAGE;
import static duke.constants.DukeOutputMessages.HEY_MESSAGE;
import static duke.constants.DukeOutputMessages.LIST_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.TASK_ADDED_MESSAGE;
import static duke.constants.DukeOutputMessages.TASK_DELETED_MESSAGE;
import static duke.constants.DukeOutputMessages.TASK_LIST_EMPTY_MESSAGE;
import static duke.constants.DukeOutputMessages.TASK_MARKED_DONE_MESSAGE;
import static duke.constants.DukeOutputMessages.TASK_NOT_IN_LIST_MESSAGE;
import static duke.constants.DukeOutputMessages.TODO_COMMAND_FORMAT;
import static duke.constants.DukeOutputMessages.UNRECOGNIZED_COMMAND_MESSAGE;


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

    public static void printHeyMessage() {
        out.println(HELLO_FROM + DUKE_LOGO);
        out.println(STRAIGHT_LINE);
        out.println(HEY_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        out.println(STRAIGHT_LINE);
        out.println(HERE_IS_TASK_LIST_MESSAGE);
        for (int i = 0; i < taskList.size(); i++) {
            String formattedTask = formatTaskToBeDisplayed(i + DISPLAYED_INDEX_OFFSET, taskList.get(i));
            out.println(formattedTask);
        }
        out.println(STRAIGHT_LINE);
    }

    public static void printTaskMarkedDoneMessage(Task doneTask) {
        out.println(STRAIGHT_LINE);
        out.println(TASK_MARKED_DONE_MESSAGE);
        out.println(INDENT + doneTask);
        out.println(STRAIGHT_LINE);
    }

    public static void printTaskAddedMessage(Task newTask, int taskListSize) {
        out.println(STRAIGHT_LINE);
        out.println(TASK_ADDED_MESSAGE);
        out.println(INDENT + newTask);
        out.println(WHITESPACE + "You now have " + taskListSize + " task(s) in the list.");
        out.println(STRAIGHT_LINE);
    }

    public static void printTaskDeletedMessage(Task deletedTask, int taskListSize) {
        out.println(STRAIGHT_LINE);
        out.println(TASK_DELETED_MESSAGE);
        out.println(INDENT + deletedTask);
        out.println(WHITESPACE + "You now have " + (taskListSize - 1) + " task(s) in the list." );
        out.println(STRAIGHT_LINE);
    }

    public static void printEmptyListMessage() {
        out.println(STRAIGHT_LINE);
        out.println(TASK_LIST_EMPTY_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    public static void printInvalidCommandFormatMessage() {
        out.println(STRAIGHT_LINE);
        out.println(COMMAND_WRONG_FORMAT_MESSAGE);
        out.println(ENTER_HELP);
        out.println(STRAIGHT_LINE);
    }

    public static void printTaskNotInListMessage() {
        out.println(STRAIGHT_LINE);
        out.println(TASK_NOT_IN_LIST_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    public static void printHelp() {
        out.println(STRAIGHT_LINE);
        out.println(HELP_INTRO_MESSAGE);
        out.println(LIST_COMMAND_FORMAT);
        out.println(DONE_COMMAND_FORMAT);
        out.println(TODO_COMMAND_FORMAT);
        out.println(DEADLINE_COMMAND_FORMAT);
        out.println(EVENT_COMMAND_FORMAT);
        out.println(DELETE_COMMAND_FORMAT);
        out.println(STRAIGHT_LINE);
    }

    public static void printFileTaskInvalidFormatMessage() {
        out.println(STRAIGHT_LINE);
        out.println(FILE_WRONG_FORMAT_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    public static void printInvalidFileTypeMessage() {
        out.println(STRAIGHT_LINE);
        out.println(INVALID_FILE_TYPE_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    public static void printUnrecognizedCommandMessage() {
        out.println(STRAIGHT_LINE);
        out.println(UNRECOGNIZED_COMMAND_MESSAGE);
        out.println(ENTER_HELP);
        out.println(STRAIGHT_LINE);
    }

    public static void printByeMessage() {
        out.println(STRAIGHT_LINE);
        out.println(BYE_MESSAGE);
        out.println(STRAIGHT_LINE);
    }

    private static String formatTaskToBeDisplayed(int displayedIndex, Task task) {
        return String.format(DISPLAYED_TASK_FORMAT, displayedIndex, task.toString());
    }

}
