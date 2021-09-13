package duke;

import duke.task.Task;
import duke.task.TaskType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.util.ArrayList;

public class TaskManager {
    private static final int TASK_NAME_INDEX = 0;
    private static final int TASK_DATE_INDEX = 1;

    private static final String LS = System.lineSeparator();
    private static final String S_TAB = "     ";
    private static final String L_TAB = "       ";

    private static final String MESSAGE_ADD_TASK = S_TAB + "NOTICE: I've added this task..." + LS
            + L_TAB + "%1$s" + LS
            + S_TAB + "Now you have %2$s task(s) in the list.";
    private static final String MESSAGE_DELETE_TASK = S_TAB + "NOTICE: I've removed this task..." + LS
            + L_TAB + "%1$s" + LS
            + S_TAB + "Now you have %2$s task(s) in the list.";
    private static final String MESSAGE_MARK_TASK_AS_DONE = S_TAB + "NOTICE: This task is marked as done..." + LS
            + L_TAB + "%1$s";
    private static final String MESSAGE_TASK_IN_LIST = S_TAB + "NOTICE: Here are the task(s) in your list...";
    private static final String MESSAGE_HELP = S_TAB + "NOTICE: This is a list of the possible commands...";

    private static final String ERROR_INVALID_TASK_SELECTED = S_TAB + "ERROR: Invalid task selected.";
    private static final String ERROR_NO_TASK_IN_LIST = S_TAB + "ERROR: There are no tasks in your list.";

    private static final String LIST_ITEM = L_TAB + "%1$s.%2$s";
    private static final String LIST_COMMAND = L_TAB + "1. list" + LS
            + L_TAB + "2. todo <TASK>" + LS
            + L_TAB + "3. deadline <TASK> /by <DATE>" + LS
            + L_TAB + "4. event <TASK> /at <DATE>" + LS
            + L_TAB + "5. done <TASK_NO>" + LS
            + L_TAB + "6. help" + LS
            + L_TAB + "7. bye";

    private static ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Getter for task array.
     *
     * @return Task array containing the lists of all tasks.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return Size of task list.
     */
    public static int getListSize() {
        return taskList.size();
    }

    /**
     * Adds a task to the list of existing tasks.
     *
     * @param taskName     The name of task to be added.
     * @param taskDate     TODO: null.
     *                     DEADLINE: The due date of the task to be added.
     *                     EVENT: The event date of the task to be added.
     * @param taskType     The type of task to be added.
     * @param printMessage Boolean value that checks if task completion message should be printed.
     */
    public static void addToList(String taskName, String taskDate, TaskType taskType, Boolean printMessage) {
        String[] information = new String[]{taskName, taskDate};
        switch (taskType) {
        case TODO:
            taskList.add(new Todo(information[TASK_NAME_INDEX]));
            break;
        case DEADLINE:
            taskList.add(new Deadline(information[TASK_NAME_INDEX], information[TASK_DATE_INDEX]));
            break;
        case EVENT:
            taskList.add(new Event(information[TASK_NAME_INDEX], information[TASK_DATE_INDEX]));
            break;
        default:
        }
        if (printMessage) {
            Picture.printLine();
            System.out.println(getMessageForAddTask());
            Picture.printLine();
        }
        Storage.saveTask();
    }

    /**
     * Constructs a generic add task message when a task is added.
     *
     * @return add task message.
     */
    private static String getMessageForAddTask() {
        final String taskDetails = taskList.get(taskList.size() - 1).toString();
        return String.format(MESSAGE_ADD_TASK, taskDetails, taskList.size());
    }

    /**
     * Deletes a task from a list of existing tasks.
     *
     * @param itemNumber One index greater than the index of the task in the list.
     * @throws DukeException If there are no tasks in the list.
     *                       If itemNumber > number of items in list or not a positive integer.
     */
    public static void deleteTask(int itemNumber) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException(ERROR_NO_TASK_IN_LIST);
        } else if (itemNumber > taskList.size() || itemNumber < 1) {
            throw new DukeException(ERROR_INVALID_TASK_SELECTED);
        } else {
            Picture.printLine();
            final String taskDetails = taskList.get(itemNumber - 1).toString();
            taskList.remove(itemNumber - 1);
            System.out.println(getMessageForDeleteTask(taskDetails));
        }
        Picture.printLine();
        Storage.saveTask();
    }

    /**
     * Constructs a generic delete task message when a task is deleted.
     *
     * @return delete task message.
     */
    private static String getMessageForDeleteTask(String taskDetails) {
        return String.format(MESSAGE_DELETE_TASK, taskDetails, taskList.size());
    }

    /**
     * Marks the task associated with the itemNumber as completed.
     * Prints a message to confirm that the task has been marked as completed.
     *
     * @param itemNumber One index greater than the index of the task in the list.
     * @param printMessage Boolean value that checks if task completion message should be printed.
     * @throws DukeException If itemNumber > number of items in list or not a positive integer.
     */
    public static void markAsCompleted(int itemNumber, Boolean printMessage) throws DukeException {
        if (itemNumber > taskList.size() || itemNumber < 1) {
            throw new DukeException(ERROR_INVALID_TASK_SELECTED);
        } else {
            taskList.get(itemNumber - 1).markTaskAsDone();
            final String taskDetails = taskList.get(itemNumber - 1).toString();
            if (printMessage) {
                Picture.printLine();
                System.out.println(getMessageForMarkTaskAsDone(taskDetails));
            }
        }
        if (printMessage) {
            Picture.printLine();
        }
        Storage.saveTask();
    }

    /**
     * Constructs a confirmation message when a task is marked as done.
     *
     * @param taskDetails String containing the description of the task.
     * @return confirmation message for a task marked as done.
     */
    private static String getMessageForMarkTaskAsDone(String taskDetails) {
        return String.format(MESSAGE_MARK_TASK_AS_DONE, taskDetails);
    }

    /**
     * Prints an error message if an invalid task is selected;
     * otherwise prints all the tasks in the list in ascending order.
     */
    public static void printList() throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException(ERROR_NO_TASK_IN_LIST);
        } else {
            Picture.printLine();
            System.out.println(MESSAGE_TASK_IN_LIST);
            printTasksInList();
        }
        Picture.printLine();
    }

    /**
     * Enumerates through all the tasks in the list and
     * prints each task line by line.
     */
    private static void printTasksInList() {
        String taskDetails;
        for (int i = 0; i < taskList.size(); i++) {
            taskDetails = taskList.get(i).toString();
            System.out.println(getListItem(i + 1, taskDetails));
        }
    }

    /**
     * Prints one task in the list.
     *
     * @param taskNumber  Integer value that the task has been assigned.
     * @param taskDetails String description of the task.
     * @return A string numbering the task and the corresponding details.
     */
    private static String getListItem(int taskNumber, String taskDetails) {
        return String.format(LIST_ITEM, taskNumber, taskDetails);
    }

    /**
     * Prints the list of all possible commands when the "help" command is used.
     */
    public static void printHelpMessage() {
        Picture.printLine();
        System.out.println(MESSAGE_HELP + LS + LIST_COMMAND);
        Picture.printLine();
    }

}
