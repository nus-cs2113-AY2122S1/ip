package filemanager;

import console.InputParser;
import error.Error;
import task.TaskManager;
import ui.Display;

import java.util.List;

public class TaskDecoder {

    public static final int TASK_CHECKBOXES_INDEX = 0;
    public static final int CHECKBOX_STATUS_INDEX = 4;
    public static final int CHECKBOX_TASK_TYPE_INDEX = 1;

    public static String getTaskType(String checkboxComponent) {
        return checkboxComponent.substring(CHECKBOX_TASK_TYPE_INDEX, CHECKBOX_TASK_TYPE_INDEX + 1);
    }

    public static Boolean isTaskCompleted(String checkboxComponent) {
        String checkboxStatus = checkboxComponent.substring(CHECKBOX_STATUS_INDEX, CHECKBOX_STATUS_INDEX + 1);
        return Display.CHECKBOX_TASK_COMPLETE.equals(checkboxStatus);
    }

    public static void addSavedTask(String fileLine, TaskManager taskManager) {
        String[] taskComponents = InputParser.getCommandComponents(fileLine);
        String taskDetails = InputParser.getTaskDetails(taskComponents);
        boolean isDone = isTaskCompleted(taskComponents[TASK_CHECKBOXES_INDEX]);
        String taskType = getTaskType(taskComponents[TASK_CHECKBOXES_INDEX]);

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

    public static void parseFile(List<String> fileData, TaskManager taskManager) {
        fileData.stream()
                .forEach((line) -> addSavedTask(line, taskManager));
    }
}
