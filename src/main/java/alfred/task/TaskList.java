package alfred.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        Task removedTask = tasks.get(index);
        tasks.remove(index);
        return removedTask;
    }

    public int getSize() {
        return tasks.size();
    }

    public void setTaskDoneInList(int index) {
        tasks.get(index).setTaskDone();
    }
}
