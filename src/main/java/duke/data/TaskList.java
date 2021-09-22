package duke.data;

import duke.data.task.Task;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public Task removeTask(int index) {
        Task task = taskList.get(index);
        taskList.remove(task);
        return task;
    }

    public void markDone(Task task) {
        task.setDone();
    }

    public Task markDone(int index) {
        Task task = taskList.get(index);
        task.setDone();
        return task;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getCount() {
        return taskList.size();
    }
}

