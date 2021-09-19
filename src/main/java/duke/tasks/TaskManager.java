package duke.tasks;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskManager {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int currentTasksCount = 0;

    public static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber <= currentTasksCount && taskNumber > 0);
    }

    public static int getCurrentTasksCount() {
        return currentTasksCount;
    }

    public static ArrayList<String> convertTasksToDataStringFormat() {
        ArrayList<String> taskDataStrings =
                (ArrayList<String>) tasks.stream()
                        .map(Task::toDataStringFormat)
                        .collect(Collectors.toList());
        return taskDataStrings;
    }

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static ArrayList<Task> filterListByDate(String date) {
        ArrayList<Task> filteredList =
                (ArrayList<Task>) tasks.stream()
                        .filter((task) -> date.equals(task.getDate()))
                        .collect(Collectors.toList());
        return filteredList;
    }

    public static String listTasks(ArrayList<Task> tasks) {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks = listOfTasks.concat("\n" + (i + 1) + ". " + tasks.get(i).toString());
        }
        return listOfTasks;
    }

    public static void clearAllTasks() {
        tasks.clear();
        currentTasksCount = 0;
    }

    public static void addTask(Task task) {
        tasks.add(task);
        currentTasksCount++;
    }

    public static Task deleteTask(int taskIndex) {
        Task deletedTask = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        currentTasksCount--;
        return deletedTask;
    }

    public static Task markTaskDone(int taskIndex) {
        Task doneTask = tasks.get(taskIndex - 1);
        doneTask.setDone();
        return doneTask;
    }
}
