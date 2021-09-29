package duke.ui;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    final public static String HORIZONTAL_LINE = "_________________________________________________________________";
    final public static String DELETE_TASK_COMMENT = "Noted. I've removed this task:";
    final public static String MARK_TASK_COMMENT = "Nice! I've marked this task as done:";
    final public static String LIST_TASK_COMMENT = "Here are the tasks in your list:";
    final public static String LIST_UPCOMING_TASKS = "Here are the upcoming deadlines in your list within the next three days:";
    final public static String ADDED_TASK_COMMENT = "Got it. I've added this task:";
    final public static String GOODBYE_COMMENT = "Bye. Hope to see you again soon!";


    public Ui() {
    }

    private static final Scanner commandScanner = new Scanner(System.in);

    /**
     * Welcomes the user with a welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showHorizontalLine();
    }

    /**
     * Returns a user input String.
     * Prompts for an input in the CLI using Scanner.
     *
     * @return User input String
     */
    public String readCommand() {
        String userInput = commandScanner.nextLine();
        return userInput;
    }

    /**
     * Handles the UI aspect of deleting a task.
     *
     * @param taskDetails ToString of task that has been deleted
     * @param taskCount   Number of tasks left after deletion
     */
    public void handleDelete(String taskDetails, int taskCount) {
        System.out.println(DELETE_TASK_COMMENT);
        String formatOutput = String.format(" %s", taskDetails);
        System.out.println(formatOutput);

        String printTaskNumber = String.format("Now you have %d items in the list.", taskCount);
        System.out.println(printTaskNumber);
    }

    /**
     * Handles 'done' command's UI aspect.
     *
     * @param task Specific task outlined for deletion.
     */
    public void handleDone(Task task) {
        System.out.println(MARK_TASK_COMMENT);
        String formatOutput = String.format(" %s", task.toString());
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
        String formatOutput = String.format("%d.%s", taskIndex, task.toString());
        System.out.println(formatOutput);
    }

    /**
     * Formats list of tasks for the 'find' command.
     * e.g. 1.[T][X] homework
     *
     * @param indexOne 1-based-index to index each task when listing.
     * @param task     Specific task within the list.
     */
    public void handleFind(int indexOne, Task task) {
        String formatOutput = String.format("%d.%s", indexOne, task.toString());
        System.out.println(formatOutput);
    }

    /**
     * Handles 'add' command's UI aspect.
     * Reiterates the number of items left in the list after addition.
     *
     * @param newTask   Specific task that was newly added.
     * @param taskCount Number of tasks in the list after addition.
     */
    public void handleAdd(Task newTask, int taskCount) {
        System.out.println(ADDED_TASK_COMMENT);
        System.out.println(newTask.toString());

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
            String formatOutput = String.format(">>> %s", task.description);
            System.out.println(formatOutput);
        }
    }

    /**
     * Displays a comment before exiting Duke.
     */
    public void handleBye() {
        System.out.println(GOODBYE_COMMENT);
    }

    /**
     * Handles the printing of error messages upon catching an exception thrown.
     *
     * @param message Error message received from parsing the user's input
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a load error if Duke fails to load the database due to I/O error.
     */
    public void showLoadingError() {
        final String LOADING_ERROR = "LOAD ERROR ... LOAD ERROR ... LOAD ERROR ...";
        System.out.println(LOADING_ERROR);
    }

    /**
     * Displays and corrects the user's input deadline format, after catching a DateTimeParseException.
     */
    public void showDeadlineError() {
        final String ERROR_WRONG_DEADLINE = "Please input your deadline in the format: d/M/yyyy HHmm :-)";
        System.out.println(ERROR_WRONG_DEADLINE);
    }

    /**
     * Displays the error message for index out of bounds error.
     */
    public void showOutOfBoundsError() {
        final String ERROR_OUT_OF_BOUNDS = "That task does not exist! Stop fooling around!";
        System.out.println(ERROR_OUT_OF_BOUNDS);
    }

    /**
     * Display a divider line in the CLI.
     */
    public void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
