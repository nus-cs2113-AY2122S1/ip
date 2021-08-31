import java.util.Arrays;

public class TaskManager {

    public static final String STATUS_DONE = "X";
    public static boolean hasInvalidIndex = false;

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private final int INDEX_DESCRIPTION = 0;
    private final int INDEX_DATETIME = 1;

    public static int getTaskCount() {
        return taskCount;
    }

    public void addToDoTask(String taskInfo) {
        tasks[taskCount] = new ToDo(taskInfo);
        DisplayManager.printCreateTask(tasks[taskCount]);
        taskCount++;
    }

    public void addDeadlineTask(String taskInfo) {
        String[] taskComponents = splitTaskComponents(taskInfo);
        tasks[taskCount] = new Deadline(taskComponents[INDEX_DESCRIPTION], taskComponents[INDEX_DATETIME]);
        DisplayManager.printCreateTask(tasks[taskCount]);
        taskCount++;
    }

    public void addEventTask(String taskInfo) {
        String[] taskComponents = splitTaskComponents(taskInfo);
        tasks[taskCount] = new Event(taskComponents[INDEX_DESCRIPTION], taskComponents[INDEX_DATETIME]);
        DisplayManager.printCreateTask(tasks[taskCount]);
        taskCount++;
    }

    public int[] filterOutOfRangeIndexes(int[] indexes) {
        int[] outOfRangeIndexes = new int[indexes.length];
        int count = 0;

        for (int index : indexes) {
            if (index - 1 >= taskCount) {
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
            if (!(index - 1 >= taskCount) && !(tasks[index - 1].getStatusIcon().equals(STATUS_DONE))) {
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
            if (!(index - 1 >= taskCount) && tasks[index - 1].getStatusIcon().equals(STATUS_DONE)) {
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
                tasks[validIndex - 1].markAsDone();
            }
        }

        DisplayManager.printSetAsDoneResult(tasks, outOfRangeIndexes, validIndexes, doneIndexes);
    }

    public void getAndPrintTaskList() {
        if (taskCount == 0) {
            DisplayManager.printErrorList();
        } else {
            DisplayManager.printMultipleTasks(Arrays.copyOf(tasks, taskCount));
        }
    }

    /**
     * @param taskInfo
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
