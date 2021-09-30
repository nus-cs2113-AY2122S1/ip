package tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> allTasks;

    public TaskList() {
        allTasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        allTasks.remove(taskIndex);
    }

    public void markTaskAsDone(int taskIndex) {
        allTasks.get(taskIndex).markAsDone();
    }

    public Task getTask(int taskIndex) {
        return allTasks.get(taskIndex);
    }

    public ArrayList<Task> getTaskList() {
        return allTasks;
    }

    public int getSize() {
        return allTasks.size();
    }

}
