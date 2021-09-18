package filemanager;

import console.InputParser;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskEncoder {

    public static final String REGEX_OPEN_BRACKET = "\\(";
    public static final String REGEX_CLOSE_BRACKET = "\\)";

    public static final int CHECKBOX_STATUS_INDEX = 4;

    public static final int START_INDEX = 0;

    public static String clearCheckboxSpace(Task task, String taskInfo) {
        if (task.getIsCompleted()) {
            return taskInfo;
        }
        return taskInfo.substring(START_INDEX, CHECKBOX_STATUS_INDEX) + taskInfo.substring(CHECKBOX_STATUS_INDEX + 1);
    }

    public static String clearDateBrackets(String taskInfo) {
        return taskInfo.replaceAll(REGEX_OPEN_BRACKET, InputParser.DATE_SEPARATOR)
                .replaceAll(REGEX_CLOSE_BRACKET, InputParser.SEPARATOR).trim();
    }

    public static String encodeTaskToString(Task task) {
        String taskToAdd = task.toString();
        taskToAdd = clearCheckboxSpace(task, taskToAdd);
        taskToAdd = clearDateBrackets(taskToAdd);
        return taskToAdd;
    }

    public static List<String> encodeTaskList(ArrayList<Task> allTasks) {
        List<String> encodedTasks = new ArrayList<>();
        allTasks.forEach(task -> encodedTasks.add(encodeTaskToString(task)));
        return encodedTasks;
    }
}
