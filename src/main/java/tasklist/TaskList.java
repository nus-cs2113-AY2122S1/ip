package tasklist;

import task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int numberOfTasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTask(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        Task.setTotalTasks(Task.getTotalTasks() + 1);
    }

    public void addTaskForSpecificCases(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        tasks.remove(index);
        Task.setTotalTasks(Task.getTotalTasks() - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}
