package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static final String LS = System.lineSeparator();
    private static final String S_TAB = "     ";
    private static final String L_TAB = "       ";

    private static final String MESSAGE_ADD_TASK = S_TAB + "NOTICE: I've added this task..." + LS
            + L_TAB + "%1$s" + LS
            + S_TAB + "Now you have %2$s task(s) in the list.";
    private static final String MESSAGE_TASK_IN_LIST = S_TAB + "NOTICE: Here are the task(s) in your list...";
    private static final String MESSAGE_DELETE_TASK = S_TAB + "NOTICE: I've removed this task..." + LS
            + L_TAB + "%1$s" + LS
            + S_TAB + "Now you have %2$s task(s) in the list.";
    private static final String MESSAGE_MARK_TASK_AS_DONE = S_TAB + "NOTICE: This task is marked as done..." + LS
            + L_TAB + "%1$s";
    private static final String MESSAGE_HELP = S_TAB + "NOTICE: This is a list of the possible commands...";

    private static final String ERROR_NO_TASK_IN_LIST = S_TAB + "ERROR: There are no tasks in your list.";
    private static final String ERROR_INVALID_TASK_SELECTED = S_TAB + "ERROR: Invalid task selected.";

    private static final String LIST_ITEM = L_TAB + "%1$s.%2$s";
    private static final String LIST_COMMAND = L_TAB + "1. list" + LS
            + L_TAB + "2. todo <TASK>" + LS
            + L_TAB + "3. deadline <TASK> /by <DATE>" + LS
            + L_TAB + "4. event <TASK> /at <DATE>" + LS
            + L_TAB + "5. done <TASK_NO>" + LS
            + L_TAB + "6. help" + LS
            + L_TAB + "7. bye";

    protected ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Gets the number of tasks in the list.
     *
     * @return Size of task list.
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the list.
     * Used when loading data from the file.
     *
     * @param task Task object to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a task to the list.
     *
     * @param task    Task object to be added.
     * @param ui      User interface of duke.
     * @param isPrint Boolean that checks if a message for task adding should be printed.
     */
    public void addTask(Task task, Ui ui, boolean isPrint) {
        tasks.add(task);
        if (isPrint) {
            ui.showLine();
            System.out.println(getMessageForAddTask());
            ui.showLine();
        }
    }

    /**
     * Gets the string representation of task details
     * to be stored as a line in the file.
     *
     * @param taskIndex Task index of the task to be stored
     * @return String representation of task details to be stored in the file.
     */
    public String getTaskStringForStorage(int taskIndex) {
        return tasks.get(taskIndex).toFileString() + LS;
    }

    /**
     * Print all the tasks in the list.
     *
     * @param ui User interface of duke.
     */
    public void printList(Ui ui) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException(ERROR_NO_TASK_IN_LIST);
        } else {
            ui.showLine();
            System.out.println(MESSAGE_TASK_IN_LIST);
            printTaskInList();
            ui.showLine();
        }
    }

    /**
     * Deletes a task in the list.
     *
     * @param taskNumber Task number of the task to be deleted.
     * @param ui         User interface of duke.
     */
    public void deleteTask(int taskNumber, Ui ui) throws DukeException {
        if (isListEmpty()) {
            throw new DukeException(ERROR_NO_TASK_IN_LIST);
        } else if (doesTaskNumberExist(taskNumber)) {
            throw new DukeException(ERROR_INVALID_TASK_SELECTED);
        } else {
            ui.showLine();
            String taskDetails = getTaskDetails(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            System.out.println(getMessageForDeleteTask(taskDetails));
            ui.showLine();
        }
    }

    /**
     * Marks a task in the list as completed.
     *
     * @param taskNumber Task number of the task to be marked as completed.
     * @param ui         User interface of duke.
     * @param isPrint    Boolean that checks if a message for task adding should be printed.
     */
    public void markAsCompleted(int taskNumber, Ui ui, boolean isPrint) throws DukeException {
        if (doesTaskNumberExist(taskNumber)) {
            throw new DukeException(ERROR_INVALID_TASK_SELECTED);
        } else {
            ui.showLine();
            markTaskAsCompleted(taskNumber - 1);
            String taskDetails = getTaskDetails(taskNumber);
            if (isPrint) {
                System.out.println(getMessageForMarkTaskAsDone(taskDetails));
            }
            ui.showLine();
        }
    }

    /**
     * Prints the list of all possible commands when the "help" command is used.
     *
     * @param ui User interface of duke.
     */
    public void printHelpMessage(Ui ui) {
        ui.showLine();
        System.out.println(MESSAGE_HELP + LS + LIST_COMMAND);
        ui.showLine();
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Get the string representation of the task details.
     *
     * @param taskIndex Task index of a task in the list.
     * @return String representation of the task details.
     */
    private String getTaskDetails(int taskIndex) {
        return tasks.get(taskIndex).toString();
    }

    /**
     * Marks a task in the list as completed.
     *
     * @param taskIndex Task index of the task to be marked as completed.
     */
    private void markTaskAsCompleted(int taskIndex) {
        tasks.get(taskIndex).markTaskAsDone();
    }

    /**
     * Gets a confirmation message after a task has been added.
     *
     * @return Confirmation message that a task has been added.
     */
    private String getMessageForAddTask() {
        String taskDetails = tasks.get(tasks.size() - 1).toString();
        return String.format(MESSAGE_ADD_TASK, taskDetails, tasks.size());
    }

    /**
     * Iterates through the list and prints each task
     */
    private void printTaskInList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(formatTaskString((i + 1), getTaskDetails(i)));
        }
    }

    /**
     * Formats the printing of task details as a string.
     *
     * @param taskNumber  Task number of the task to be printed.
     * @param taskDetails Task details of the task to be printed.
     * @return Formatted string of the task details.
     */
    private String formatTaskString(int taskNumber, String taskDetails) {
        return String.format(LIST_ITEM, taskNumber, taskDetails);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True: If list is empty.
     *         False: If list is non-empty.
     */
    private boolean isListEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Checks if the list has a given task number.
     *
     * @param taskNumber Task number of the task to be checked.
     * @return True: If task number exists within the list.
     *         False: If task number does not exist within the list.
     */
    private boolean doesTaskNumberExist(int taskNumber) {
        return taskNumber > tasks.size() || taskNumber < 1;
    }

    /**
     * Returns a formatted confirmation message after a task has been deleted.
     *
     * @param taskDetails Task details of the task to be deleted.
     * @return Formatted confirmation message of task deletion.
     */
    private String getMessageForDeleteTask(String taskDetails) {
        return String.format(MESSAGE_DELETE_TASK, taskDetails, tasks.size());
    }

    /**
     * Returns a formatted confirmation message after a task has been mark as completed.
     *
     * @param taskDetails Task details of the task to be mark as completed.
     * @return Formatted confirmation message of task mark as completed.
     */
    private String getMessageForMarkTaskAsDone(String taskDetails) {
        return String.format(MESSAGE_MARK_TASK_AS_DONE, taskDetails);
    }
}
