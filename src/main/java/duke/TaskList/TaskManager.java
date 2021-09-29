package duke.TaskList;

import duke.TaskList.task.Deadline;
import duke.TaskList.task.Event;
import duke.TaskList.task.Task;
import duke.TaskList.task.ToDo;
import duke.Ui.DisplayManager;
import duke.Storage.FileManager;
import duke.Ui.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class responsible for managing and storing all types of tasks.
 */
public class TaskManager {

    public static final String STATUS_DONE = "X";
    public static boolean hasInvalidIndex = false;

    private static ArrayList<Task> tasks = new ArrayList<>();

    private final int INDEX_DESCRIPTION = 0;
    private final int INDEX_DATETIME = 1;

    public static int getTaskCount() {
        return tasks.size();
    }

    /**
     * Creates a new 'ToDo' type task and adds it into the list of tasks.
     * Displays a message to the user upon completion.
     * @param taskInfo String containing the description of the task.
     */
    public void addToDoTask(String taskInfo) {
        try {
            Task newTask = new ToDo(taskInfo);
            tasks.add(newTask);
            DisplayManager.printCreateTask(newTask);
            FileManager.updateFile(tasks);
        } catch (IOException e) {
            DisplayManager.printErrorFileUpdate();
        }
    }

    /**
     * Creates a new 'Deadline' type task and adds it into the list of tasks.
     * Displays a message to the user upon completion.
     * @param parser Parser object used for parsing operations.
     * @param taskInfo String containing the description and date time of the task.
     */
    public void addDeadlineTask(Parser parser, String taskInfo) {
        try {
            String[] taskComponents = parser.splitTaskComponents(taskInfo);
            Task newTask = new Deadline(taskComponents[INDEX_DESCRIPTION], taskComponents[INDEX_DATETIME]);
            tasks.add(newTask);
            DisplayManager.printCreateTask(newTask);
            FileManager.updateFile(tasks);
        } catch (IOException e) {
            DisplayManager.printErrorFileUpdate();
        }
    }

    /**
     * Creates a new 'Event' type task and adds it into the list of tasks.
     * Displays a message to the user upon completion.
     * @param parser Parser object used for parsing operations.
     * @param taskInfo String containing the description and date time of the task.
     */
    public void addEventTask(Parser parser, String taskInfo) {
        try {
            String[] taskComponents = parser.splitTaskComponents(taskInfo);
            Task newTask = new Event(taskComponents[INDEX_DESCRIPTION], taskComponents[INDEX_DATETIME]);
            tasks.add(newTask);
            DisplayManager.printCreateTask(newTask);
            FileManager.updateFile(tasks);
        } catch (IOException e) {
            DisplayManager.printErrorFileUpdate();
        }
    }

    /**
     * Creates a new 'Todo' type task and adds it into the list of tasks when loading tasks from storage file.
     * Updates the status of task according to the information from the storage file.
     * @param taskInfo String containing the details of the saved task.
     * @param isTaskDone Boolean value for task completed status.
     */
    public void addSavedToDo(String taskInfo, boolean isTaskDone) {
        Task savedTask = new ToDo(taskInfo);
        tasks.add(savedTask);
        if (isTaskDone) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * Creates a new 'Deadline' type task and adds it into the list of tasks when loading tasks from storage file.
     * Updates the status of task according to the information from the storage file.
     * @param parser Parser object used for parsing operations.
     * @param taskInfo String containing the details of the saved task.
     * @param isTaskDone Boolean value for task completed status.
     */
    public void addSavedDeadline(Parser parser, String taskInfo, boolean isTaskDone) {
        String[] taskComponents = parser.splitTaskComponents(taskInfo);
        Task savedTask = new Deadline(taskComponents[INDEX_DESCRIPTION], taskComponents[INDEX_DATETIME]);
        tasks.add(savedTask);
        if (isTaskDone) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * Creates a new 'Event' type task and adds it into the list of tasks when loading from storage file.
     * Updates the status of the task according to the information from the storage file.
     * @param parser Parser object used for parsing operations.
     * @param taskInfo String containing the details of the saved task.
     * @param isTaskDone Boolean value for task completed status.
     */
    public void addSavedEvent(Parser parser, String taskInfo, boolean isTaskDone) {
        String[] taskComponents = parser.splitTaskComponents(taskInfo);
        Task savedTask = new Event(taskComponents[INDEX_DESCRIPTION], taskComponents[INDEX_DATETIME]);
        tasks.add(savedTask);
        if (isTaskDone) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * Filters out and returns the indexes of tasks that are out of range of the task list.
     * @param indexes Array of integers containing the indexes of tasks taken in consideration.
     * @return Array of integers containing indexes that are out of range.
     */
    private int[] filterOutOfRangeIndexes(int[] indexes) {
        int[] outOfRangeIndexes = new int[indexes.length];
        int count = 0;

        for (int index : indexes) {
            if (index - 1 >= tasks.size()) {
                outOfRangeIndexes[count] = index;
                count++;
            }
        }

        if (count == 0) {
            return null;
        }
        return Arrays.copyOf(outOfRangeIndexes, count);
    }

    /**
     * Filters out and returns the indexes of tasks that are valid in the task list when setting tasks as done.
     * @param indexes Array of integers containing the indexes of tasks taken in consideration.
     * @return Array of integers containing the indexes of tasks that are valid to set as done.
     */
    private int[] filterValidIndexes(int[] indexes) {
        int[] validIndexes = new int[indexes.length];
        int count = 0;

        for (int index : indexes) {
            if (!(index - 1 >= tasks.size()) && !(tasks.get(index - 1).getStatusIcon().equals(STATUS_DONE))) {
                validIndexes[count] = index;
                count++;
            }
        }

        if (count == 0) {
            return null;
        }
        return Arrays.copyOf(validIndexes, count);
    }

    /**
     * Filters out and returns the indexes of tasks that are valid in the task list when deleting tasks.
     * @param indexes Array of integers containing the indexes of tasks taken in consideration.
     * @return Array of integers containing the indexes of tasks that are valid to delete.
     */
    private int[] filterValidDeleteIndexes(int[] indexes) {
        int[] validIndexes = new int[indexes.length];
        int count = 0;

        for (int index : indexes) {
            if (!(index - 1>= tasks.size()) ) {
                validIndexes[count] = index;
                count++;
            }
        }

        if (count == 0) {
            return null;
        }
        return Arrays.copyOf(validIndexes, count);
    }

    /**
     * Filters out and returns the indexes of tasks that are done.
     * @param indexes Array of integers containing the indexes of tasks taken in consideration.
     * @return Array of integers containing the indexes of tasks that have been done.
     */
    private int[] filterDoneIndexes(int[] indexes) {
        int[] doneIndexes = new int[indexes.length];
        int count = 0;

        for (int index : indexes) {
            if (!(index - 1 >= tasks.size()) && tasks.get(index - 1).getStatusIcon().equals(STATUS_DONE)) {
                doneIndexes[count] = index;
                count++;
            }
        }

        if (count == 0) {
            return null;
        }
        return Arrays.copyOf(doneIndexes, count);
    }

    /**
     * Filters indexes of tasks from the input that are out of range, valid, and done,
     * then marks all tasks with valid indexes as done.
     * Displays to the users the tasks that are set as done.
     * Displays to the users the indexes of tasks that are out of range.
     * Displays to the users the indexes of tasks that have been done before.
     * @param taskInfo String containing the indexes of tasks to be set as done.
     */
    public void setAsDone(String taskInfo) {
        int[] indexes = filterIndexes(taskInfo);
        int[] outOfRangeIndexes = filterOutOfRangeIndexes(indexes);
        int[] validIndexes = filterValidIndexes(indexes);
        int[] doneIndexes = filterDoneIndexes(indexes);

        if (validIndexes != null) {
            for (int validIndex : validIndexes) {
                tasks.get(validIndex - 1).markAsDone();
            }
        }

        try {
            FileManager.updateFile(tasks);
        } catch (IOException e) {
            DisplayManager.printErrorMarkDoneUpdate();
        }
        DisplayManager.printSetAsDoneResult(tasks, outOfRangeIndexes, validIndexes, doneIndexes);
    }

    /**
     * Filters indexes of tasks from the input that are out of range, and valid,
     * then deletes all the tasks with the valid indexes.
     * Displays to the users the tasks that are successfully deleted.
     * Displays to the users the indexes of tasks that are out of range.
     * @param taskInfo String containing the indexes of tasks to be deleted.
     */
    public void deleteTask(String taskInfo) {
        int[] indexes = filterIndexes(taskInfo);
        int[] outOfRangeIndexes = filterOutOfRangeIndexes(indexes);
        int[] validIndexes = filterValidDeleteIndexes(indexes);
        ArrayList<Task> deletedTasks = new ArrayList<>();

        if (validIndexes != null) {
            for (int validIndex : validIndexes) {
                deletedTasks.add(tasks.get(validIndex - 1));
                tasks.set(validIndex - 1, null);
            }
            tasks.removeIf(Objects::isNull);
        }

        try {
            FileManager.updateFile(tasks);
        } catch (IOException e) {
            DisplayManager.printErrorDeleteUpdate();
        }
        DisplayManager.printDeleteTasksResult(deletedTasks, outOfRangeIndexes, tasks.size());
    }

    /**
     * Retrieves and displays all the tasks in the current task list.
     */
    public void getAndPrintTaskList() {
        if (tasks.size() == 0) {
            DisplayManager.printErrorList();
        } else {
            DisplayManager.printMultipleTasks(tasks);
        }
    }

    /** Splits the input string into elements and filters out those that are numbers from those
     * that are not.
     * Displays the elements that are non-integer.
     * Returns an array of integers containing integer indexes.
     * @param taskInfo String from user input containing all possible indexes.
     * @return Array of integers containing integer indexes.
     */
    private static int[] filterIndexes(String taskInfo) {
        String[] inputs = taskInfo.split(" ");
        int[] indexes = new int[inputs.length];
        int indexCount = 0;
        String[] invalidIndexes = new String[inputs.length];
        int invalidCount = 0;

        for (String input : inputs) {
            try {
                int index = Integer.parseInt(input);
                indexes[indexCount] = index;
                indexCount++;
            } catch (NumberFormatException nfe) {
                invalidIndexes[invalidCount] = input;
                invalidCount++;
                hasInvalidIndex = true;
            }
        }

        if (invalidCount != 0) {
            DisplayManager.printErrorIndex(invalidIndexes, invalidCount);
        }

        return Arrays.copyOf(indexes, indexCount);
    }
}
