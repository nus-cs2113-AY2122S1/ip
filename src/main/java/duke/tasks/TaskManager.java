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

    /**
     * Gets the current task list.
     *
     * @return <code>ArrayList</code> of all <code>Task</code> that is stored
     */
    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Filters the current task list for tasks that have descriptions that contain a certain string.
     *
     * @param filterWord String to filter the tasks
     * @return <code>ArrayList</code> of <code>Task</code> that contains <code>filterWord</code>
     */
    public static ArrayList<Task> filterListByKeyword(String filterWord) {
        ArrayList<Task> filteredList =
                (ArrayList<Task>) tasks.stream()
                        .filter((task) -> task.getDescription().toLowerCase().contains(filterWord.toLowerCase()))
                        .collect(Collectors.toList());
        return filteredList;
    }

    /**
     * Filters the current task list for tasks that have a specific date attached.
     *
     * @param date Date to filter the tasks
     * @return <code>ArrayList</code> of <code>Task</code> that has the specific <code>date</code> attached
     */
    public static ArrayList<Task> filterListByDate(String date) {
        ArrayList<Task> filteredList =
                (ArrayList<Task>) tasks.stream()
                        .filter((task) -> date.equals(task.getDate()))
                        .collect(Collectors.toList());
        return filteredList;
    }

    /**
     * Converts all the <code>Task</code> given to string format, then concatenated as one string, for
     * display on the user interface.
     *
     * @param tasks <code>ArrayList</code> of <code>Task</code> to convert to string format
     * @return String that concatenates all the string format of all <code>Task</code> in the list
     */
    public static String listTasks(ArrayList<Task> tasks) {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks = listOfTasks.concat("\n" + (i + 1) + ". " + tasks.get(i).toString());
        }
        return listOfTasks;
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
