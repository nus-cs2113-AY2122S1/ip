import java.util.Arrays;

public class TaskManager {
    private static boolean hasInvalidIndex = false;

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

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
        tasks[taskCount] = new Deadline(taskComponents[0], taskComponents[1]);
        DisplayManager.printCreateTask(tasks[taskCount]);
        taskCount++;
    }

    public void addEventTask(String taskInfo) {
        String[] taskComponents = splitTaskComponents(taskInfo);
        tasks[taskCount] = new Event(taskComponents[0], taskComponents[1]);
        DisplayManager.printCreateTask(tasks[taskCount]);
        taskCount++;
    }

    public void setAsDone(String taskInfo) {
        int[] indexes = filterIndexes(taskInfo);
        int[] invalidIndexes = new int[indexes.length];
        int[] validIndexes = new int[indexes.length];
        int[] doneIndexes = new int[indexes.length];
        int invalidCount = 0;
        int validCount = 0;
        int doneCount = 0;

        for (int index : indexes) {
            if (index - 1 >= taskCount) {
                invalidIndexes[invalidCount] = index;
                invalidCount++;
            } else if (tasks[index - 1].getStatusIcon().equals("X")) {
                doneIndexes[doneCount] = index;
                doneCount++;
            } else {
                tasks[index - 1].markAsDone();
                validIndexes[validCount] = index;
                validCount++;
            }
        }

        if (invalidCount + validCount + doneCount == 0 && hasInvalidIndex) {
            System.out.println("   ____________________________________________________________");
            hasInvalidIndex = false;
            return;
        } else if (invalidCount + validCount + doneCount == 0) {
            return;
        }

        if (!hasInvalidIndex) {
            System.out.println("   ____________________________________________________________");
        } else {
            hasInvalidIndex = false;
            System.out.print("\n");
        }

        if (validCount != 0) {
            System.out.println("       Nice! I've marked these tasks as done:");
            for (int i = 0; i < validCount; i++) {
                System.out.println("         [X] " + tasks[validIndexes[i] - 1].getDescription());
            }
        }

        if (doneCount != 0) {
            System.out.print("\n");
            for (int j = 0; j < doneCount; j++) {
                System.out.println("       Ignoring entry " + doneIndexes[j] + " as it has been done before.");
            }
        }

        if (invalidCount != 0) {
            System.out.print("\n");
            for (int i = 0; i < invalidCount; i++) {
                System.out.println("       Entry " + invalidIndexes[i] + " does not exist.");
            }
        }

        System.out.println("   ____________________________________________________________");
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
            System.out.println("   ____________________________________________________________");
            for (int i = 0; i < invalidCount; i++) {
                System.out.println("       " + invalidIndexes[i] + " is not a valid index.");
            }
        }

        return Arrays.copyOf(indexes, indexCount);
    }
}
