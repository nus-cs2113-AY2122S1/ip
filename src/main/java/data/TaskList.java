package data;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> tasks) {
        setTaskList(tasks);
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void deleteTask(int taskID) {
        Task targetTask = tasks.get(taskID - 1);
        tasks.remove(targetTask);
    }

    public void doneTask(int taskID) {
        tasks.get(taskID - 1).setStatus(true);
    }

    public void setTaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public String getTaskInfo(int taskID) {
        Task currentTask = tasks.get(taskID);
        return currentTask.toString();
    }

    public int getSize() {
        return tasks.size();
    }
}
