package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    /** List of Task objects */
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public int getSize() {
        return tasks.size();
    }
}
