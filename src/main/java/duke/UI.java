package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The UI class handles all printing of messages to the terminal.
 */
public class UI{
    private static final String BUFFER_LINE = "____________________________________________________________\n";

    /**
     * Prints a welcome message including the logo of Duke.
     */
    public static void printWelcomeMessage() {
        System.out.println(BUFFER_LINE);
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String GREETING = BUFFER_LINE
                + LOGO
                + BUFFER_LINE
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + BUFFER_LINE;
        System.out.println(GREETING);
    }

    /**
     * Prints a goodbye message before ending the program.
     */
    public static void printEndMessage() {
        String BYE_MESSAGE = BUFFER_LINE
                + " Bye. Hope to see you again soon!\n"
                + BUFFER_LINE;
        System.out.println(BYE_MESSAGE);
    }

    /**
     * Prints an addition message whenever a new task is being added.
     * @param current The current task being added.
     * @param taskCount The current number of tasks.
     */
    public static void printAdditionMessage(Task current, int taskCount) {
        String additionMessage = BUFFER_LINE
                + " Gotcha! I've added this task: \n"
                + "    " + current.listTask() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n"
                + BUFFER_LINE;
        System.out.println(additionMessage);
    }

    /**
     * Prints a confirmation message whenever a task is set as done.
     * @param current The current task being set as done.
     */
    public static void printDoneMessage(Task current) {
        String doneMessage = BUFFER_LINE
                + " Nice! I've marked this task as done: \n"
                + "    " + current.listTask() + "\n"
                + BUFFER_LINE;
        System.out.println(doneMessage);
    }

    /**
     * Prints a deletion message whenever a task is being delete.
     * @param current The current task being deleted.
     * @param taskCount The current number of tasks.
     */
    public static void printDeleteMessage(Task current, int taskCount) {
        String doneMessage = BUFFER_LINE
                + " Alright! I've removed this task from the list: \n"
                + "    " + current.listTask() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n"
                + BUFFER_LINE;
        System.out.println(doneMessage);
    }

    /**
     * Prints the current list of all tasks in chronological order of being added.
     * @param tasks The existing list of tasks.
     */
    public static void printList(ArrayList<Task> tasks) {
        System.out.println(BUFFER_LINE);
        System.out.println(" Here are the tasks in your list:");
        int i = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(" " + i + ". " + task.listTask());
            i = i + 1;
        }
        System.out.println(BUFFER_LINE);
    }

    /**
     * Prints the current list of all tasks that contain the filter string.
     * @param tasks The existing list of tasks.
     * @param filterString The filter string input from user.
     */
    public static void printList(ArrayList<Task> tasks, String filterString) {
        System.out.println(BUFFER_LINE);
        System.out.println(" Here are the tasks in your list containing '" + filterString + "' :");
        int i = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(" " + i + ". " + task.listTask());
            i = i + 1;
        }
        System.out.println(BUFFER_LINE);
    }

    /**
    * Prints an error message indicating that there are no existing tasks.
    */
    public static void printEmptyListMessage() {
        String EMPTY_LIST_MESSAGE = BUFFER_LINE
                + "You have 0 tasks in your list!\n"
                + BUFFER_LINE;
        System.out.println(EMPTY_LIST_MESSAGE);
    }

    /**
     * Prints an error message indicating that there are no existing tasks
     * containing the specified filter string.
     * @param filterString The filter string input from user.
     */
    public static void printEmptyListMessage(String filterString) {
        String EMPTY_LIST_MESSAGE = BUFFER_LINE
                + "You have 0 tasks in your list containing '" + filterString + "' !\n"
                + BUFFER_LINE;
        System.out.println(EMPTY_LIST_MESSAGE);
    }
    /**
     * Prints an error message indicating what error has occurred.
     * @param e Exception that has been thrown.
     */
    public static void printExceptionMessage(Exception e) {
        System.out.println(e);
    }

    /**
     * Prints a message to indicate that a wrong format was used but is still readable.
     */
    public static void printWrongButOkayMessage() {
        String WRONG_INPUT_BUT_OKAY_MESSAGE = "This has a wrong format but I think you want this!";
        System.out.println(WRONG_INPUT_BUT_OKAY_MESSAGE);
    }
}
