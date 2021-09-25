package task;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void setTaskList(ArrayList<Task> taskList) {
        if (taskList != null) {
            this.taskList = taskList;
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> savedTaskList) {
        taskList.addAll(savedTaskList);
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void deleteTask(Task t) {
        taskList.remove(t);
    }

    public void markTaskAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void clearHistory() {
        taskList.clear();
    }
}
