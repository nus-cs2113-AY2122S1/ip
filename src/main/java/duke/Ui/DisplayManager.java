package duke.Ui;

import duke.TaskList.TaskManager;
import duke.TaskList.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for displaying messages and errors to the user.
 */
public class DisplayManager {

    public static final String DISPLAY_HORIZONTAL_SEPARATOR = "    _________________________" +
            "___________________________________";
    public static final String DISPLAY_MESSAGE_INDENT = "        ";
    public static final String DISPLAY_TASK_INDENT = "    ";
    private static final String DISPLAY_LOGO = "    ____        _        \n"
            + "   |  _ \\ _   _| | _____ \n"
            + "   | | | | | | | |/ / _ \\\n"
            + "   | |_| | |_| |   <  __/\n"
            + "   |____/ \\__,_|_|\\_\\___|";
    private static final String DISPLAY_GREET_START = DISPLAY_HORIZONTAL_SEPARATOR + "\n" +
            "        Hello! I'm Duke\n" +
            "        What can I do for you?\n" +
            DISPLAY_HORIZONTAL_SEPARATOR;
    private static final String DISPLAY_GREET_END = DISPLAY_HORIZONTAL_SEPARATOR + "\n" +
            "        Bye. Hope to see you again soon!\n" +
            DISPLAY_HORIZONTAL_SEPARATOR;

    /**
     * Displays the welcome message.
     */
    public void printStartGreet() {
        System.out.println(DISPLAY_LOGO);
        System.out.println(DISPLAY_GREET_START);
    }

    /**
     * Displays the goodbye message.
     */
    public void printEndGreet() {
        System.out.println(DISPLAY_GREET_END);
    }

    /**
     * Displays the horizontal separator.
     */
    public static void printHorizontalSeparator() {
        System.out.println(DISPLAY_HORIZONTAL_SEPARATOR);
    }

    /**
     * Creates and returns a UI placeholder to store strings by using square brackets.
     * @param content String to be inserted between the brackets.
     * @return String containing the fully made UI element.
     */
    private static String createBox(String content) {
        return "[" + content + "]";
    }

    /**
     * Creates and returns two UI placeholders containing task type and task completion status.
     * @param taskType Single character string containing the type of the task.
     * @param taskStatus Single marking character to mark status of the task.
     * @return String containing the fully made UI element.
     */
    public static String createListBox(String taskType, String taskStatus) {
        return createBox(taskType) + createBox(taskStatus);
    }

    /**
     * Creates and displays the message for successfully creating a new task.
     * @param task Task successfully added into the list of tasks.
     */
    public static void printCreateTask(Task task) {
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "Got it. I've added this task:");
        System.out.println(DISPLAY_MESSAGE_INDENT + DISPLAY_TASK_INDENT + task);
        System.out.println(DISPLAY_MESSAGE_INDENT + "Now you have " + (TaskManager.getTaskCount()) + " tasks in the list.");
        printHorizontalSeparator();
    }

    /**
     * Creates and displays the message for listing all the tasks in the current task list.
     * @param tasks ArrayList of tasks in the task list.
     */
    public static void printMultipleTasks(ArrayList<Task> tasks) {
        int counter = 0;
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(DISPLAY_MESSAGE_INDENT + (counter + 1) + ". " + task);
            counter++;
        }
        printHorizontalSeparator();
    }

    /**
     * Creates and displays the message of tasks successfully set as done.
     * @param tasks ArrayList of tasks to set as done.
     * @param validIndexes Array of integers containing the indexes of tasks successfully set as done.
     */
    private static void printMultipleValidSetDone(ArrayList<Task> tasks, int[] validIndexes) {
        System.out.println(DisplayManager.DISPLAY_MESSAGE_INDENT + "Nice! I've marked these tasks as done:");
        for (int validIndex : validIndexes) {
            System.out.println(DISPLAY_MESSAGE_INDENT + DISPLAY_TASK_INDENT + tasks.get(validIndex - 1));
        }
    }

    /**
     * Creates and displays the message of tasks that were already done before when setting tasks as done.
     * @param doneIndexes Array of integer containing the indexes of tasks that were already done before.
     */
    private static void printMultipleAlreadyDone(int[] doneIndexes) {
        System.out.print("\n");
        for (int doneIndex : doneIndexes) {
            System.out.println(DISPLAY_MESSAGE_INDENT + "Ignoring entry " + doneIndex
                    + " as it has been done before.");
        }
    }

    /**
     * Creates and displays the message of tasks that were successfully deleted.
     * @param deletedTasks ArrayList of tasks that were deleted.
     */
    private static void printMultipleValidDelete(ArrayList<Task> deletedTasks) {
        if (!deletedTasks.isEmpty()) {
            System.out.println(DisplayManager.DISPLAY_MESSAGE_INDENT + "Noted. I've removed this task:");
            for (Task deletedTask : deletedTasks) {
                System.out.println(DISPLAY_MESSAGE_INDENT + DISPLAY_TASK_INDENT + deletedTask);
            }
        }
    }

    /**
     * Creates and displays the message of tasks that were out of range.
     * @param outOfRangeIndexes Array of integers containing the indexes of tasks that are out of range.
     */
    private static void printMultipleOutOfRange(int[] outOfRangeIndexes) {
        System.out.print("\n");
        for (int outOfRangeIndex : outOfRangeIndexes) {
            System.out.println(DISPLAY_MESSAGE_INDENT + "Entry " + outOfRangeIndex + " does not exist.");
        }
    }

    /**
     * Creates and displays the whole message of the results after setting tasks as done.
     * @param tasks ArrayList of tasks in the task list.
     * @param outOfRangeIndexes Array of integers containing indexes of tasks that are out of range.
     * @param validIndexes Array of integers containing indexes of tasks that are valid to set as done.
     * @param doneIndexes Array of integers containing indexes of tasks already done before.
     */
    public static void printSetAsDoneResult(
            ArrayList<Task> tasks, int[] outOfRangeIndexes, int[] validIndexes, int[] doneIndexes) {

        int outOfRangeCount, validIndexCount, doneIndexCount;

        if (outOfRangeIndexes == null) {
            outOfRangeCount = 0;
        } else {
            outOfRangeCount = outOfRangeIndexes.length;
        }
        if (validIndexes == null) {
            validIndexCount = 0;
        } else {
            validIndexCount = validIndexes.length;
        }
        if (doneIndexes == null) {
            doneIndexCount = 0;
        } else {
            doneIndexCount = doneIndexes.length;
        }

        if (outOfRangeCount + validIndexCount + doneIndexCount == 0 && TaskManager.hasInvalidIndex) {
            printHorizontalSeparator();
            TaskManager.hasInvalidIndex = false;
            return;
        } else if (outOfRangeCount + validIndexCount + doneIndexCount == 0) {
            return;
        }

        if (!TaskManager.hasInvalidIndex) {
            printHorizontalSeparator();
        } else {
            TaskManager.hasInvalidIndex = false;
            System.out.print("\n");
        }

        if (validIndexCount != 0) {
            printMultipleValidSetDone(tasks, validIndexes);
        }
        if (doneIndexCount != 0) {
            printMultipleAlreadyDone(doneIndexes);
        }
        if (outOfRangeCount != 0) {
            printMultipleOutOfRange(outOfRangeIndexes);
        }

        printHorizontalSeparator();
    }

    /**
     * Creates and displays the whole message of the results after deleting tasks.
     * @param deletedTasks ArrayList of tasks deleted successfully.
     * @param outOfRangeIndexes Array of integers containing indexes of tasks that are out of range.
     * @param taskCount Number of tasks in the task list.
     */
    public static void printDeleteTasksResult(ArrayList<Task> deletedTasks, int[] outOfRangeIndexes, int taskCount) {
        int outOfRangeCount;

        if (outOfRangeIndexes == null) {
            outOfRangeCount = 0;
        } else {
            outOfRangeCount = outOfRangeIndexes.length;
        }

        if (outOfRangeCount == 0 && deletedTasks.isEmpty() && TaskManager.hasInvalidIndex) {
            printHorizontalSeparator();
            TaskManager.hasInvalidIndex = false;
            return;
        } else if (outOfRangeCount == 0 && deletedTasks.isEmpty()) {
            return;
        }

        if (!TaskManager.hasInvalidIndex) {
            printHorizontalSeparator();
        } else {
            TaskManager.hasInvalidIndex = false;
            System.out.print("\n");
        }

        printMultipleValidDelete(deletedTasks);
        if (outOfRangeCount != 0) {
            printMultipleOutOfRange(outOfRangeIndexes);
        }
        if (!deletedTasks.isEmpty()) {
            System.out.println(DISPLAY_MESSAGE_INDENT + "Now you have " + taskCount + " tasks in your list.");
        }
        printHorizontalSeparator();
    }

    /**
     * Creates and displays the whole message of results after searching for a keyword name in task list.
     */
    public static void printFindResult(List<Task> queryResults) {
        int counter = 0;
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "Here are the matching tasks in your list:");
        for (Task queryResult : queryResults) {
            System.out.println(DISPLAY_MESSAGE_INDENT + (counter + 1) + ". " + queryResult);
        }
        printHorizontalSeparator();
    }

    /**
     * Creates and displays error message of having no tasks when listing all the tasks in the task list.
     */
    public static void printErrorList() {
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "No tasks found in the list.");
        printHorizontalSeparator();
    }

    /**
     * Creates and displays error message of indexes that are invalid.
     * @param invalidIndexes Array of strings containing invalid task indexes.
     * @param invalidCount Number of invalid indexes.
     */
    public static void printErrorIndex(String[] invalidIndexes, int invalidCount) {
        printHorizontalSeparator();
        for (int i = 0; i < invalidCount; i++) {
            System.out.println(DISPLAY_MESSAGE_INDENT + invalidIndexes[i] + " is not a valid index.");
        }
    }

    /**
     * Creates and displays error message of loading data from storage file.
     */
    public void printErrorLoadingData() {
        printHorizontalSeparator();
        System.out.println( DISPLAY_MESSAGE_INDENT + "Error while trying to load data file");
        printHorizontalSeparator();
    }

    /**
     * Creates and displays error message of updating data into storage file.
     */
    public static void printErrorFileUpdate() {
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "Error while trying to update tasks into file");
        printHorizontalSeparator();
    }

    /**
     * Creates and displays error message of updating status of tasks into storage file.
     */
    public static void printErrorMarkDoneUpdate() {
        printHorizontalSeparator();
        System.out.println(DISPLAY_MESSAGE_INDENT + "Error occurred while updating file after marking tasks as done.");
        printHorizontalSeparator();
    }

    /**
     * Creates and displays error message of updating storage file after deleting tasks.
     */
    public static void printErrorDeleteUpdate() {
        printHorizontalSeparator();
        System.out.println("Error occurred when trying to save file after deleting task.");
        printHorizontalSeparator();
    }
}
