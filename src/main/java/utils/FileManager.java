package utils;

import console.InputParser;
import error.Error;
import task.Task;
import task.TaskManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class FileManager {

    public static final String FILE_PATH = "data/savedTasks.txt";

    public static final String REGEX_OPEN_BRACKET = "\\(";
    public static final String REGEX_CLOSE_BRACKET = "\\)";

    public static final int CHECKBOX_STATUS_INDEX = 4;
    public static final int CHECKBOX_TASK_TYPE_INDEX = 1;

    public static final int START_INDEX = 0;

    public static void addSavedTask(String taskType, Boolean isDone, String taskDetails, TaskManager taskManager) {
        switch (taskType) {
        case Display.CHECKBOX_TODO_TASK_TYPE:
            taskManager.addSavedTodoTask(isDone, taskDetails);
            break;
        case Display.CHECKBOX_DEADLINE_TASK_TYPE:
            taskManager.addSavedDeadlineTask(isDone, taskDetails);
            break;
        case Display.CHECKBOX_EVENT_TASK_TYPE:
            taskManager.addSavedEventTask(isDone, taskDetails);
            break;
        default:
            Error.displayFileAddTaskError();
            break;
        }
    }

    public static void parseFile(TaskManager taskManager) throws IOException {
        File taskFile = new File(FILE_PATH);
        taskFile.getParentFile().mkdir();
        if (taskFile.createNewFile()) {
            return;
        }
        Scanner s = new Scanner(taskFile);
        while (s.hasNext()) {
            String[] taskComponents = InputParser.getCommandComponents(s);
            String taskDetails = InputParser.getTaskDetails(taskComponents);
            addSavedTask(getTaskType(taskComponents[0]), isTaskCompleted(taskComponents[0]), taskDetails, taskManager);
        }
    }

    public static String getTaskType(String checkboxComponent) {
        return checkboxComponent.substring(CHECKBOX_TASK_TYPE_INDEX, CHECKBOX_TASK_TYPE_INDEX + 1);
    }

    public static Boolean isTaskCompleted(String checkboxComponent) {
        String checkboxStatus = checkboxComponent.substring(CHECKBOX_STATUS_INDEX, CHECKBOX_STATUS_INDEX + 1);
        return Display.CHECKBOX_TASK_COMPLETE.equals(checkboxStatus);
    }

    public static void loadFileData(TaskManager taskManager) {
        try {
            parseFile(taskManager);
        } catch (IOException e) {
            Error.displayFileCreateError();
        }
    }

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

    public static void updateFileData(ArrayList<Task> allTasks) throws IOException {
        FileWriter taskFile = new FileWriter(FILE_PATH);
        for (Task task : allTasks) {
            String taskToAdd = task.toString();
            taskToAdd = clearCheckboxSpace(task, taskToAdd);
            taskToAdd = clearDateBrackets(taskToAdd);
            taskFile.write(taskToAdd);
            taskFile.write(System.lineSeparator());
        }
        taskFile.close();
    }
}
