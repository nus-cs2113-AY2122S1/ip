package duke.tasks;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskManager {

    private static final ArrayList<Task> TASKS = new ArrayList<>();
    private static int currentTasksCount = 0;

    public static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber <= currentTasksCount && taskNumber > 0);
    }

    public static int getCurrentTasksCount() {
        return currentTasksCount;
    }

    public static ArrayList<String> convertTasksToDataStringFormat() {
        ArrayList<String> taskDataStrings =
                (ArrayList<String>) TASKS.stream()
                        .map(Task::toDataStringFormat)
                        .collect(Collectors.toList());
        return taskDataStrings;
    }

    public void clearAllTasks() {
        TASKS.clear();
        currentTasksCount = 0;
    }

    public void addTask(Task task) {
        TASKS.add(task);
        currentTasksCount++;
    }

    public String listTasks() {
        String listOfTasks = "";
        for (int i = 0; i < currentTasksCount; i++) {
            listOfTasks = listOfTasks.concat("\n" + (i + 1) + ". " + TASKS.get(i).toString());
        }
        return listOfTasks;
    }

    public Task deleteTask(int taskIndex) {
        Task deletedTask = TASKS.get(taskIndex - 1);
        TASKS.remove(taskIndex - 1);
        currentTasksCount--;
        return deletedTask;
    }

    public Task markTaskDone(int taskIndex) {
        Task doneTask = TASKS.get(taskIndex - 1);
        doneTask.setDone();
        return doneTask;
    }
}
