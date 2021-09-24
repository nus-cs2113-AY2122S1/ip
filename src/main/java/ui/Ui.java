package ui;

import exception.AustinException;
import task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static task.TaskList.getTaskItemInString;
import static task.TaskList.tasksCount;

/** Interacts with user by printing messages and task details */
public class Ui {
    private final String logo =
                    "        ___      __    __       _______.___________.__  .__   __.\n" +
                    "       /   \\    |  |  |  |     /       |           |  | |  \\ |  |\n" +
                    "      /  ^  \\   |  |  |  |    |   (----`---|  |----|  | |   \\|  |\n" +
                    "     /  /_\\  \\  |  |  |  |     \\   \\       |  |    |  | |  . `  |\n" +
                    "    /  _____  \\ |  `--'  | .----)   |      |  |    |  | |  |\\   |\n" +
                    "   /__/     \\__\\ \\______/  |_______/       |__|    |__| |__| \\__|  ";

    private final String WELCOME_MESSAGE = "Hello from\n" + logo
            + "\nHello! I'm Austin.\n"
            + "What can I do for you?\n"
            + "In case, if you are unsure of any commands, please type \"help\".";
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n"
            + "___________________SHUTTING DOWN______________________";
    private final String ACTIVATED_MESSAGE =
            "___________________COMMAND ACTIVATED__________________";
    private final String EXCEPTION_DIVIDER =
            "______________________________________________________";
    private final String COMPLETION_MESSAGE =
            "___________________COMMAND EXECUTED___________________\n"
            + "Anything else?\n"
            + "In case, if you are unsure of any commands, please type \"help\".";
    private final Scanner SCANNER = new Scanner(System.in);

    private static final String ACKNOWLEDGE_DONE_MESSAGE =
            "Amazing! I have marked this task as done:";
    private static final String ACKNOWLEDGE_NOT_DONE_MESSAGE =
            "Noted. I have marked this task as \"not done\":";
    private static final String ACKNOWLEDGE_DELETE_MESSAGE =
            "Noted. I have deleted the following task:";
    private static final String ACKNOWLEDGE_ADD_MESSAGE =
            "Noted. I have successfully added this task:";
    private static final String ACKNOWLEDGE_CLEAR_MESSAGE =
            "All the tasks are cleared from the list.";
    private static final String DATE_TIME_PARSE_EXCEPTION_MESSAGE_WHILE_LOADING = "We are unable to parse the date and " +
            "time details. So, a new list will be created.\nSorry for your inconvenience.";

    public Ui() {
        printWelcomeMessage();
    }

    /**
     * Prints welcome message along with the logo.
     */
    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Prints message and closing line after the command is executed
     * (except for "bye" command).
     */
    public void printCompletionMessage() {
        System.out.println(COMPLETION_MESSAGE);
    }

    /**
     * Prints closing message and finishing line after "bye" command is called.
     */
    public void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Prints message after executing the command
     * @param isExit To check if "bye" command is called
     */
    public void showFinishingLine(boolean isExit) {
        if (isExit) {
            printExitMessage();
        } else {
            printCompletionMessage();
        }
    }

    public void printExceptionDivider() {
        System.out.println(EXCEPTION_DIVIDER);
    }

    /**
     * Prints immediately after user keys in the command
     */
    public void printActivatedMessage() {
        System.out.println(ACTIVATED_MESSAGE);
    }

    /**
     * Prints exception message when the IO exception is triggered.
     * @param e IOException triggered
     */
    public void showIOError(IOException e) {
        System.out.println("IO Exception: " + e.getMessage());
    }

    /**
     * Prints the exception message when the AustinException is triggered
     * @param e AustinException triggered
     */
    public void showError(AustinException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints message when the NumberFormatException or IndexOutOfBoundsException is
     * triggered.
     */
    public void showNumberFormatError() {
        System.out.println("Oops. The task index is invalid. " +
                "Please try again with a valid number.");
    }

    /**
     * Prints message when the ArrayIndexOutOfBoundsException is triggered if the user
     * failed to provide the task index when either "done" or "undo" or "delete" is called.
     */
    public void showMissingTaskIndexError() {
        System.out.println("Oops. You have missed out on the task index.");
    }

    /**
     * Prints an exception message when the date and time parsing fails while extracting the details
     * in the file.
     */
    public void showDateTimeParseExceptionErrorWhileLoading() {
        System.out.println(DATE_TIME_PARSE_EXCEPTION_MESSAGE_WHILE_LOADING);
    }

    public void showDateTimeParseExceptionError() {
        System.out.println("Oops. The date and time given are in the wrong format.\n"
                + "Please follow the correct format (d/m/yyyy hhmm).");
    }

    /**
     * Prints message stating the number of the tasks in the list.
     * @param count Number of tasks in the list
     */
    public static void printCurrentStatus(int count) {
        if (count == 0) {
            System.out.println("Currently, you have no tasks in your list.");
        } else if (count == 1) {
            System.out.println("Currently, you have only 1 task in your list.");
        } else {
            System.out.println("Currently, you have " + count + " tasks in your list.");
        }
    }

    /**
     * Prints message stating the number of the tasks in the list. This is called
     * immediately after adding a new task into the list.
     * @param count Number of tasks in the list after adding
     */
    public void printCurrentStatusAfterAdding(int count) {
        if (count == 1) {
            System.out.println("Now, you have only 1 task in the list.");
        } else {
            System.out.println("Now, you have " + count + " tasks in the list.");
        }
    }

    /** Reads the command input by the user. */
    public String readCommand() {
        return SCANNER.nextLine().trim();
    }

    /**
     * Prints specific task item in a string format.
     * @param taskIndex Index of the task in the array list
     */
    public static void printTaskItem(int taskIndex) {
        System.out.println(getTaskItemInString(taskIndex));
    }

    /**
     * Prints confirmation message after the task is marked as done.
     * @param taskIndex Index of the task in the list
     */
    public void acknowledgeDone(int taskIndex) {
        System.out.println(ACKNOWLEDGE_DONE_MESSAGE);
        printTaskItem(taskIndex);
    }

    /**
     * Prints confirmation message after the task is marked as "not done".
     * @param taskIndex Index of the task in the list
     */
    public void acknowledgeUndo(int taskIndex) {
        System.out.println(ACKNOWLEDGE_NOT_DONE_MESSAGE);
        printTaskItem(taskIndex);
    }

    /**
     * Prints confirmation message deleting the task.
     * @param removedTask Deleted task
     */
    public void acknowledgeDelete(Task removedTask) {
        System.out.println(ACKNOWLEDGE_DELETE_MESSAGE);
        System.out.println(removedTask.toString());
    }

    /**
     * Prints confirmation message after a new task is added into the list.
     */
    public void acknowledgeAdd() {
        int num = tasksCount();
        System.out.println(ACKNOWLEDGE_ADD_MESSAGE);
        printTaskItem(num - 1);
        printCurrentStatusAfterAdding(num);
    }

    /**
     * Prints confirmation message after clearing the tasks stored in the list.
     */
    public void acknowledgeClear() {
        System.out.println(ACKNOWLEDGE_CLEAR_MESSAGE);
    }

    /**
     * Prints all the tasks from the list in a string format.
     * @param tasks List containing task objects
     */
    public static void printList(ArrayList<Task> tasks) {
        int index = 1;
        for (Task task: tasks) {
            System.out.println(index + ". " + task.toString());
            index++;
        }
    }

    /**
     * Prints deadline tasks due today and event tasks happening today.
     * @param todayTasks List containing the deadline and event tasks
     */
    public void printAgenda(ArrayList<Task> todayTasks) {
        if (todayTasks.size() == 0) {
            System.out.println("There are no deadlines and events today.");
        }
        System.out.println("Today's agenda:");
        printList(todayTasks);
    }
}