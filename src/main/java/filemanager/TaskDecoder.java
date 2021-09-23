package filemanager;

import console.InputParser;
import error.DukeTaskNameEmptyException;
import error.Error;
import task.TaskManager;
import ui.Display;

import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Decodes the tasks saved in storage into task objects to be stored in TaskManager.
 */
public class TaskDecoder {

    /** Index position of the checkboxes pair */
    public static final int TASK_CHECKBOXES_INDEX = 0;

    public static final int CHECKBOX_TASK_TYPE_INDEX = 1;
    public static final int CHECKBOX_STATUS_INDEX = 4;

    /**
     * Returns a single character representation of the different task types
     *
     * @param checkboxComponent Checkbox string that contains the task type.
     * @return Task type present in the checkbox.
     */
    public static String getTaskType(String checkboxComponent) {
        return checkboxComponent.substring(CHECKBOX_TASK_TYPE_INDEX, CHECKBOX_TASK_TYPE_INDEX + 1);
    }

    /**
     * Returns true if the status in the checkbox is marked as completed.
     *
     * @param checkboxComponent Checkbox string that contains the status.
     * @return The status found in the checkbox.
     */
    public static Boolean isTaskCompleted(String checkboxComponent) {
        String checkboxStatus = checkboxComponent.substring(CHECKBOX_STATUS_INDEX, CHECKBOX_STATUS_INDEX + 1);
        return Display.CHECKBOX_TASK_COMPLETE.equals(checkboxStatus);
    }

    /**
     * Checks the task type and adds the corresponding task object into the TaskManager.
     *
     * @param fileLine Single line of task data stored in the storage file.
     * @param taskManager TaskManager that stores the saved task data.
     * @throws IndexOutOfBoundsException If saved task details is in the wrong format.
     * @throws DukeTaskNameEmptyException If saved task does not have a name.
     * @throws DateTimeParseException If saved task date information is in the wrong format.
     */
    public static void addSavedTask(String fileLine, TaskManager taskManager) throws IndexOutOfBoundsException,
            DukeTaskNameEmptyException, DateTimeParseException {
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

    /**
     * Parses all lines in the storage file and save all task data into TaskManager.
     *
     * @param fileData All lines of task data present in the storage file.
     * @param taskManager TaskManager that stores the saved task data.
     */
    public static void parseFile(List<String> fileData, TaskManager taskManager) {
        fileData.stream()
                .forEach((line) -> {
                        try {
                            addSavedTask(line, taskManager);
                        } catch (IndexOutOfBoundsException e) {
                            Error.displayFileSavedTaskFormatError();
                        } catch (DukeTaskNameEmptyException e) {
                            Error.displayFileSavedTaskNameEmptyError();
                        } catch (DateTimeParseException e) {
                            Error.displayFileSavedDateFormatError();
                        }
                    });
    }
}
