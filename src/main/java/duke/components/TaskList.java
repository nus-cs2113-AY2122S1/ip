package duke.components;

import duke.task.Task;

import java.util.ArrayList;

/**
 * A class that contains the task list.
 */
public class TaskList {

    protected ArrayList<Task> tasks;

    /**
     * Class constructor.
     * Creates an empty ArrayList of Task.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the ArrayList of Task.
     *
     * @return tasks The ArrayList of Task.
     */
    public ArrayList<Task> getTasksList() {
        return tasks;
    }

    /**
     * Set the value of tasks
     *
     * @param tasks The ArrayList of Task to be set to tasks.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the total number of tasks.
     *
     * @return The total number of tasks.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Adds a new task to tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a tasks knowing its index (0-based)
     *
     * @param index Index of the task to be returned (0-based).
     * @return The task having the given index.
     */
    public Task getTaskByIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from tasks.
     *
     * @param index Index of the task to be removed (0-based).
     */
    public void removeTaskByIndex(int index) {
        tasks.remove(index);
    }
    
}
