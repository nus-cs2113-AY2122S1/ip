package tasklist;

import task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        Task.setTotalTasks(Task.getTotalTasks() + 1);

    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        tasks.remove(index);
        Task.setTotalTasks(Task.getTotalTasks() - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}
