package duke.tasks;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a utility class that contains methods to perform operations on a static list
 * of <code>Task</code>.
 */
public class TaskManager {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int currentTasksCount = 0;

    /**
     * To check if the specified <code>taskNumber</code> represents a <code>Task</code> in the list.
     *
     * @param taskNumber The number to check for validity.
     * @return <p><code>true</code> - if the number has a corresponding <code>Task</code> in the list</p>
     *         <p><code>false</code> - otherwise</p>
     */
    public static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber <= currentTasksCount && taskNumber > 0);
    }

    /**
     * Getter method for the number of <code>Task</code> stored in the list.
     *
     * @return Total number of <code>Task</code>
     */
    public static int getCurrentTasksCount() {
        return currentTasksCount;
    }

    /**
     * Converts all the existing <code>Task</code> in the list to data string format for storage.
     *
     * @return <code>ArrayList</code> of <code>String</code> that contains all the <code>Task</code>
     * in data string format
     */
    public static ArrayList<String> convertTasksToDataStringFormat() {
        ArrayList<String> taskDataStrings =
                (ArrayList<String>) tasks.stream()
                        .map(Task::toDataStringFormat)
                        .collect(Collectors.toList());
        return taskDataStrings;
    }

    /** Clears all the tasks stored in task list. */
    public static void clearAllTasks() {
        tasks.clear();
        currentTasksCount = 0;
    }

    /**
     * Adds a <code>Task</code> to the current task list.
     *
     * @param task <code>Task</code> to be added
     */
    public static void addTask(Task task) {
        tasks.add(task);
        currentTasksCount++;
    }

    /**
     * Converts all the <code>Task</code> in the list to string format, then concatenated as one string, for
     * display on the user interface.
     *
     * @return String that concatenates all the string format of all <code>Task</code> in the list
     */
    public static String listTasks() {
        String listOfTasks = "";
        for (int i = 0; i < currentTasksCount; i++) {
            listOfTasks = listOfTasks.concat("\n" + (i + 1) + ". " + tasks.get(i).toString());
        }
        return listOfTasks;
    }

    /**
     * Deletes a <code>Task</code> from the current task list.
     *
     * @param taskIndex Number corresponding to the <code>Task</code> in the list
     * @return <code>Task</code> object that is deleted
     */
    public static Task deleteTask(int taskIndex) {
        Task deletedTask = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        currentTasksCount--;
        return deletedTask;
    }

    /**
     * Marks a <code>Task</code> done in the current task list.
     *
     * @param taskIndex Number corresponding to the <code>Task</code> in the list
     * @return <code>Task</code> object that is marked as done
     */
    public static Task markTaskDone(int taskIndex) {
        Task doneTask = tasks.get(taskIndex - 1);
        doneTask.setDone();
        return doneTask;
    }
}
