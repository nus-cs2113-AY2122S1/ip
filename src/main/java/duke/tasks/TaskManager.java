package duke.tasks;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a utility class that contains methods to perform operations on a list
 * of {@code Task}.
 */
public class TaskManager {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int currentTasksCount = 0;

    /**
     * To check if as specified number represents a {@code Task} in the list.
     *
     * @param taskNumber number to check for validity.
     * @return
     * <p>{@code true} - if the number has a corresponding {@code Task} in the list</p>
     * <p>{@code false} - otherwise</p>
     */
    public static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber <= currentTasksCount && taskNumber > 0);
    }

    /**
     * Getter method for the number of {@code Task} stored in the list.
     *
     * @return Total number of {@code Task}
     */
    public static int getCurrentTasksCount() {
        return currentTasksCount;
    }

    /**
     * Converts all the existing {@code Task} in the list to data string format for storage.
     *
     * @return list that contains all the {@code Task} in data string format
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
     * @return list of all {@code Task}
     */
    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Filters the current task list for tasks that have descriptions that contain a certain string.
     *
     * @param filterWord String to filter the tasks
     * @return list of {@code Task} that contains {@code filterWord}
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
     * @return list of {@code Task} that has the specific {@code date} attached
     */
    public static ArrayList<Task> filterListByDate(String date) {
        ArrayList<Task> filteredList =
                (ArrayList<Task>) tasks.stream()
                        .filter((task) -> date.equals(task.getDate()))
                        .collect(Collectors.toList());
        return filteredList;
    }

    /**
     * Converts all the {@code Task} given to string format, then concatenated as one string, for
     * display on the user interface.
     *
     * @param tasks list of {@code Task} to convert to string format
     * @return concatenated string of all {@code Task} in the list in string format
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
     * Adds a {@code Task} to the current task list.
     *
     * @param task {@code Task} to be added
     */
    public static void addTask(Task task) {
        tasks.add(task);
        currentTasksCount++;
    }

    /**
     * Deletes a {@code Task} from the current task list.
     *
     * @param taskIndex Number corresponding to the {@code Task} in the list
     * @return {@code Task} that is deleted
     */
    public static Task deleteTask(int taskIndex) {
        Task deletedTask = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        currentTasksCount--;
        return deletedTask;
    }

    /**
     * Marks a {@code Task} done in the current task list.
     *
     * @param taskIndex Number corresponding to the {@code Task} in the list
     * @return {@code Task} that is marked as done
     */
    public static Task markTaskDone(int taskIndex) {
        Task doneTask = tasks.get(taskIndex - 1);
        doneTask.setDone();
        return doneTask;
    }
}
