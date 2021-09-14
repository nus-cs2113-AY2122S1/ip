package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TaskManager {

    public static final String STATUS_DONE = "X";
    public static boolean hasInvalidIndex = false;

    private static ArrayList<Task> tasks = new ArrayList<>();

    private final int INDEX_DESCRIPTION = 0;
    private final int INDEX_DATETIME = 1;

    public static int getTaskCount() {
        return tasks.size();
    }

    public void addToDoTask(String taskInfo) {
        try {
            Task newTask = new ToDo(taskInfo);
            tasks.add(newTask);
            DisplayManager.printCreateTask(newTask);
            FileManager.updateFile(tasks);
        } catch (IOException e) {
            System.out.println("File update error"); //fix this
        }
    }

    public void addDeadlineTask(String taskInfo) {
        try {
            String[] taskComponents = splitTaskComponents(taskInfo);
            Task newTask = new Deadline(taskComponents[INDEX_DESCRIPTION], taskComponents[INDEX_DATETIME]);
            tasks.add(newTask);
            DisplayManager.printCreateTask(newTask);
            FileManager.updateFile(tasks);
        } catch (IOException e) {
            System.out.println("File update error"); //fix this
        }
    }

    public void addEventTask(String taskInfo) {
        try {
            String[] taskComponents = splitTaskComponents(taskInfo);
            Task newTask = new Event(taskComponents[INDEX_DESCRIPTION], taskComponents[INDEX_DATETIME]);
            tasks.add(newTask);
            DisplayManager.printCreateTask(newTask);
            FileManager.updateFile(tasks);
        } catch (IOException e) {
            System.out.println("File update error"); //fix this
        }
    }

    public void addSavedToDo(String taskInfo, boolean taskIsDone) {
        Task savedTask = new ToDo(taskInfo);
        tasks.add(savedTask);
        if (taskIsDone) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    public void addSavedDeadline(String taskInfo, boolean taskIsDone) {
        String[] taskComponents = splitTaskComponents(taskInfo);
        Task savedTask = new Deadline(taskComponents[INDEX_DESCRIPTION], taskComponents[INDEX_DATETIME]);
        tasks.add(savedTask);
        if (taskIsDone) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    public void addSavedEvent(String taskInfo, boolean taskIsDone) {
        String[] taskComponents = splitTaskComponents(taskInfo);
        Task savedTask = new Event(taskComponents[INDEX_DESCRIPTION], taskComponents[INDEX_DATETIME]);
        tasks.add(savedTask);
        if (taskIsDone) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    public int[] filterOutOfRangeIndexes(int[] indexes) {
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

    public int[] filterValidIndexes(int[] indexes) {
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

    public int[] filterValidDeleteIndexes(int[] indexes) {
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

    public int[] filterDoneIndexes(int[] indexes) {
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
            System.out.println("Error occurred when trying to save file after marking as done.");
        }
        DisplayManager.printSetAsDoneResult(tasks, outOfRangeIndexes, validIndexes, doneIndexes);
    }

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
            System.out.println("Error occurred when trying to save file after deleting task.");
        }
        DisplayManager.printDeleteTasksResult(deletedTasks, outOfRangeIndexes, tasks.size());
    }

    public void getAndPrintTaskList() {
        if (tasks.size() == 0) {
            DisplayManager.printErrorList();
        } else {
            DisplayManager.printMultipleTasks(tasks);
        }
    }

    /**
     * @param taskInfo contains the information of the task
     * @return taskComponents -> index 0: description, and index 1: dateTime
     */
    public static String[] splitTaskComponents(String taskInfo) {
        String[] taskComponents;
        taskComponents = taskInfo.split("/");

        for (int i = 0; i < taskComponents.length; i++) {
            String taskComponent = taskComponents[i].replaceAll("by", "");
            taskComponent = taskComponent.replaceAll("at", "");
            taskComponents[i] = taskComponent.trim();
        }

        return taskComponents;
    }

    public static int[] filterIndexes(String taskInfo) {
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
