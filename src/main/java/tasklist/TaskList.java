package tasklist;

import tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTaskToList(Task task) {
        tasks.add(task);
    }

    public void removeTaskToList(Task task) {
        tasks.remove(task);
    }

    public static void clearAllTasksFromList() {
        tasks.clear();
    }

    public int getListSize() {
        return tasks.size();
    }

    public Task getTaskFromList(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
