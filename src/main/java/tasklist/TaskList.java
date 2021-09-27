package tasklist;

import task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int numberOfTasksOfSameDate;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numberOfTasksOfSameDate = 0;
    }

    public int getNumberOfTasksOfSameDate() {
        return numberOfTasksOfSameDate;
    }

    public void setNumberOfTaskForTheSameDate(int numberOfTasks) {
        this.numberOfTasksOfSameDate = numberOfTasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        Task.setTotalTasks(Task.getTotalTasks() + 1);

    }

    public void addTaskForListOfSpecificDate(Task task) {
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
