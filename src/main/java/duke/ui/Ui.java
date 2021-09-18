package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

import static duke.constants.DukeConstants.*;
import static duke.constants.DukeOutputMessages.*;
import static duke.constants.DukeDataStorageConstants.*;


public class Ui {

    /** Offset to  differentiate between 0-based and 1-based indexing */
    private static final int DISPLAYED_INDEX_OFFSET = 1;

    /** Format of task to be printed in list */
    private static final String DISPLAYED_TASK_FORMAT = " %1$d. %2$s";

    public static void printHeyMessage() {
        System.out.println("Hello from" + System.lineSeparator() + DUKE_LOGO);
        System.out.println(STRAIGHT_LINE);
        System.out.println(HEY_MESSAGE);
        System.out.println(STRAIGHT_LINE);
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        System.out.println(STRAIGHT_LINE);
        System.out.println(HERE_IS_TASK_LIST_MESSAGE);
        for (int i = 0; i < taskList.size(); i++) {
            String formattedTask = formatTaskToBeDisplayed(i + DISPLAYED_INDEX_OFFSET, taskList.get(i));
            System.out.println(formattedTask);
        }
        System.out.println(STRAIGHT_LINE);
    }

    public static void printTaskMarkedDoneMessage(Task doneTask) {
        System.out.println(STRAIGHT_LINE);
        System.out.println(TASK_MARKED_DONE_MESSAGE);
        System.out.println(INDENT + doneTask);
        System.out.println(STRAIGHT_LINE);
    }

    public static void printTaskAddedMessage(Task newTask, int taskListSize) {
        System.out.println(STRAIGHT_LINE);
        System.out.println(TASK_ADDED_MESSAGE);
        System.out.println(INDENT + newTask);
        System.out.println(WHITESPACE + "You now have " + taskListSize + " task(s) in the list.");
        System.out.println(STRAIGHT_LINE);
    }

    public static void printTaskDeletedMessage(Task deletedTask, int taskListSize) {
        System.out.println(STRAIGHT_LINE);
        System.out.println(TASK_DELETED_MESSAGE);
        System.out.println(INDENT + deletedTask);
        System.out.println(WHITESPACE + "You now have " + (taskListSize - 1) + " task(s) in the list." );
        System.out.println(STRAIGHT_LINE);
    }

    public static void printEmptyListMessage() {
        System.out.println(STRAIGHT_LINE);
        System.out.println(TASK_LIST_EMPTY_MESSAGE);
        System.out.println(STRAIGHT_LINE);
    }

    public static void printInvalidCommandFormatMessage() {
        System.out.println(STRAIGHT_LINE);
        System.out.println(COMMAND_WRONG_FORMAT_MESSAGE);
        System.out.println(ENTER_HELP);
        System.out.println(STRAIGHT_LINE);
    }

    public static void printTaskNotInListMessage() {
        System.out.println(STRAIGHT_LINE);
        System.out.println(TASK_NOT_IN_LIST_MESSAGE);
        System.out.println(STRAIGHT_LINE);
    }

    public static void printHelp() {
        System.out.println(STRAIGHT_LINE);
        System.out.println(HELP_INTRO_MESSAGE);
        System.out.println(LIST_COMMAND_FORMAT);
        System.out.println(DONE_COMMAND_FORMAT);
        System.out.println(TODO_COMMAND_FORMAT);
        System.out.println(DEADLINE_COMMAND_FORMAT);
        System.out.println(EVENT_COMMAND_FORMAT);
        System.out.println(DELETE_COMMAND_FORMAT);
        System.out.println(STRAIGHT_LINE);
    }

    public static void printFileTaskInvalidFormatMessage() {
        System.out.println(STRAIGHT_LINE);
        System.out.println(FILE_WRONG_FORMAT_MESSAGE);
        System.out.println(STRAIGHT_LINE);
    }

    public static void printUnrecognizedCommandMessage() {
        System.out.println(STRAIGHT_LINE);
        System.out.println(UNRECOGNIZED_COMMAND_MESSAGE);
        System.out.println(ENTER_HELP);
        System.out.println(STRAIGHT_LINE);
    }

    public static void printByeMessage() {
        System.out.println(STRAIGHT_LINE);
        System.out.println(BYE_MESSAGE);
        System.out.println(STRAIGHT_LINE);
    }

    private static String formatTaskToBeDisplayed(int displayedIndex, Task task) {
        return String.format(DISPLAYED_TASK_FORMAT, displayedIndex, task.toString());
    }

}
