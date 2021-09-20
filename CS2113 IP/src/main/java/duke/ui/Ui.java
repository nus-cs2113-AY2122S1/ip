package duke.ui;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    final private static String HORIZONTAL_LINE = "_________________________________________________________________";
    final private static String DELETE_TASK_COMMENT = "Noted. I've removed this task:";
    final private static String MARK_TASK_COMMENT = "Nice! I've marked this task as done:";
    final private static String LIST_TASK_COMMENT = "Here are the tasks in your list:";
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

    public void handleDelete(int userInputInt, int taskCount, ArrayList<Task> tasks) {
        System.out.println(DELETE_TASK_COMMENT);

        String printTask = String.format(" [%s][ ] %s",
                tasks.get(userInputInt).taskType, tasks.get(userInputInt).description);
        tasks.remove(userInputInt);
        System.out.println(printTask);

        String printTaskNumber = String.format("Now you have %d items in the list.", taskCount);
        System.out.println(printTaskNumber);
    }

    public void handleDone(int userInputInt, ArrayList<Task> tasks) {
        System.out.println(MARK_TASK_COMMENT);

        String formatOutput = String.format("[%s][%s] %s",
                tasks.get(userInputInt).taskType, tasks.get(userInputInt).getStatusIcon(), tasks.get(userInputInt).description);
        System.out.println(formatOutput);
    }

    public void handleListComment() {
        System.out.println(LIST_TASK_COMMENT);
    }

    public void handleListFormat(int indexZero, ArrayList<Task> tasks) {
        int indexOne = indexZero + 1;
        String formatOutput = String.format("%d.[%s][%s] %s",
                indexOne, tasks.get(indexZero).taskType, tasks.get(indexZero).getStatusIcon(), tasks.get(indexZero).description);
        System.out.println(formatOutput);
    }

    public void handleAdd(Task newTask, int taskCount) {
        System.out.println(ADDED_TASK_COMMENT);

        String printTask = String.format(" [%s][ ] %s", newTask.taskType, newTask.description);
        System.out.println(printTask);

        String printTaskNumber = String.format("Now you have %d items in the list.", taskCount);
        System.out.println(printTaskNumber);
    }

    public static void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
