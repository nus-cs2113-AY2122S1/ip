package duke.task;

import java.util.ArrayList;

public class TaskManager {

    private static int currentTasksCount = 0;

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber <= currentTasksCount && taskNumber > 0);
    }

    public static int getCurrentTasksCount() {
        return currentTasksCount;
    }

    public void clearAllTasks() {
        tasks.clear();
        currentTasksCount = 0;
    }

    public Task addTodo(String taskDescription) {
        tasks.add(new ToDo(taskDescription));
        currentTasksCount++;
        return tasks.get(currentTasksCount - 1);
    }

    public Task addDeadline(String taskDescription, String taskDue) {
        tasks.add(new Deadline(taskDescription, taskDue));
        currentTasksCount++;
        return tasks.get(currentTasksCount - 1);
    }

    public Task addEvent(String taskDescription, String eventDateTime) {
        tasks.add(new Event(taskDescription, eventDateTime));
        currentTasksCount++;
        return tasks.get(currentTasksCount - 1);
    }

    public void listTasks() {
        for (int i = 0; i < currentTasksCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public Task markTaskDone(int taskIndex) {
        Task doneTask = tasks.get(taskIndex - 1);
        doneTask.setDone();
        return doneTask;
    }
}
