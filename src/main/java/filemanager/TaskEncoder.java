package filemanager;

import console.InputParser;
import task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Encodes and saves the task object in the storage file.
 */
public class TaskEncoder {

    /** Pattern to identify opening brackets */
    public static final String REGEX_OPEN_BRACKET = "\\(";

    /** Pattern to identify closing brackets */
    public static final String REGEX_CLOSE_BRACKET = "\\)";

    /** Index position of checkbox status mark in string */
    public static final int CHECKBOX_STATUS_INDEX = 4;

    public static final int START_INDEX = 0;

    /**
     * Removes a space in a checkbox string if it exists.
     *
     * @param task Task object with task status information.
     * @param taskInfo Task details loaded from storage file.
     * @return Task details without space in its status checkbox.
     */
    public static String clearCheckboxSpace(Task task, String taskInfo) {
        if (task.getIsCompleted()) {
            return taskInfo;
        }
        return taskInfo.substring(START_INDEX, CHECKBOX_STATUS_INDEX) + taskInfo.substring(CHECKBOX_STATUS_INDEX + 1);
    }

    /**
     * Replaces the opening bracket around the task date information with a date separator.
     * Removes the closing bracket around the task date information.
     *
     * @param taskInfo Task details loaded from storage file.
     * @return Task details with a date separator separating the task date information from task name.
     */
    public static String clearDateBrackets(String taskInfo) {
        return taskInfo.replaceAll(REGEX_OPEN_BRACKET, InputParser.DATE_SEPARATOR)
                .replaceAll(REGEX_CLOSE_BRACKET, InputParser.SEPARATOR).trim();
    }

    /**
     * Convert the string representation of task to another format for saving.
     *
     * @param task Task object to encode.
     * @return Task contents to be added to storage file.
     */
    public static String encodeTaskToString(Task task) {
        String taskToAdd = task.toString();
        taskToAdd = clearCheckboxSpace(task, taskToAdd);
        taskToAdd = clearDateBrackets(taskToAdd);
        return taskToAdd;
    }

    /**
     * Converts all the tasks in TaskManager to another format for saving.
     *
     * @param allTasks All Task objects in TaskManager.
     * @return All tasks to be added to storage file.
     */
    public static List<String> encodeTaskList(ArrayList<Task> allTasks) {
        List<String> encodedTasks = new ArrayList<>();
        allTasks.forEach(task -> encodedTasks.add(encodeTaskToString(task)));
        return encodedTasks;
    }
}
