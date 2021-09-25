package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    final private static String HORIZONTAL_LINE = "_________________________________________________________________";
    final private static String DELETE_TASK_COMMENT = "Noted. I've removed this task:";
    final private static String MARK_TASK_COMMENT = "Nice! I've marked this task as done:";
    final private static String LIST_TASK_COMMENT = "Here are the tasks in your list:";
    final private static String LIST_UPCOMING_TASKS = "Here are the upcoming deadlines in your list within the next three days:";
    final private static String ADDED_TASK_COMMENT = "Got it. I've added this task:";

    public Ui() {
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showLoadingError() {
        String LOADING_ERROR = "LOAD ERROR ... LOAD ERROR ... LOAD ERROR ...";
        System.out.println(LOADING_ERROR);
    }

    /**
     * Handles 'delete' command's UI aspect, formatting the task deleted.
     * Reiterates the number of items left in the list after deletion.
     *
     * @param task              Specific task outlined for deletion.
     * @param tasks             List of tasks.
     * @param zeroIndexInputInt User's input integer converted to 0-based-index for array manipulation.
     * @param taskCount         Number of tasks in the list after deletion.
     */
    public void handleDelete(Task task, ArrayList<Task> tasks, int zeroIndexInputInt, int taskCount) {
        System.out.println(DELETE_TASK_COMMENT);

        String printTask = String.format(" [%s][%s] %s", task.taskType, task.getStatusIcon(), task.description);
        tasks.remove(zeroIndexInputInt);
        System.out.println(printTask);

        String printTaskNumber = String.format("Now you have %d items in the list.", taskCount);
        System.out.println(printTaskNumber);
    }

    /**
     * Handles 'done' command's UI aspect, formatting the task to be mark as done.
     * e.g. [T][X] homework
     *
     * @param task Specific task outlined for deletion.
     */
    public void handleDone(Task task) {
        System.out.println(MARK_TASK_COMMENT);

        String formatOutput = String.format("[%s][%s] %s", task.taskType, task.getStatusIcon(), task.description);
        System.out.println(formatOutput);
    }

    public void handleListComment() {
        System.out.println(LIST_TASK_COMMENT);
    }

    public void handleUpcomingComment() {
        System.out.println(LIST_UPCOMING_TASKS);
    }

    /**
     * Formats list of tasks for the 'list' command.
     * e.g. 1.[T][X] homework
     *
     * @param taskIndex 1-based-index to index each task when listing.
     * @param task      Specific task within the list.
     */
    public void handleListFormat(int taskIndex, Task task) {
        String formatOutput = String.format("%d.[%s][%s] %s",
                taskIndex, task.taskType,
                task.getStatusIcon(), task.description);
        System.out.println(formatOutput);
    }

    public void handleFind(int indexOne, Task task) {
        String formatOutput = String.format("%d.[%s][%s] %s", indexOne, task.taskType,
                task.getStatusIcon(), task.description);
        System.out.println(formatOutput);
    }

    /**
     * Handles 'add' command's UI aspect, formatting the task that was just added.
     * Reiterates the number of items left in the list after addition.
     *
     * @param newTask   Specific task that was newly added.
     * @param taskCount Number of tasks in the list after addition.
     */
    public void handleAdd(Task newTask, int taskCount) {
        System.out.println(ADDED_TASK_COMMENT);

        String printTask = String.format(" [%s][] %s", newTask.taskType, newTask.description);
        System.out.println(printTask);

        String printTaskNumber = String.format("Now you have %d items in the list.", taskCount);
        System.out.println(printTaskNumber);
    }

    /**
     * Handles 'upcoming' command's UI aspect, formatting the task to be listed if it is upcoming and uncompleted.
     * If task is three days away and is yet to be completed, print task in the following format:
     * >>> [TASK_DESCRIPTION]
     *
     * @param task            Specified task to be checked whether it is upcoming or completed.
     * @param isThreeDaysAway Boolean value of whether task is due within three days of current date time.
     * @param isDone          Boolean value of whether task is completed.
     */
    public void handleUpcoming(Task task, boolean isThreeDaysAway, boolean isDone) {
        boolean isNotDone = !isDone;
        if (isThreeDaysAway && isNotDone) {
            String printTask = String.format(">>> %s", task.description);
            System.out.println(printTask);
        }
    }

    public static void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
