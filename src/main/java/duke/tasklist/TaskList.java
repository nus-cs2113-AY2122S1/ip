package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the task list and contains methods to add and remove tasks.
 */
public class TaskList {
    /** List of Task objects */
    private final ArrayList<Task> tasks;

    /**
     * Constructs TaskList object with no tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds task to the internal tasklist.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from the internal tasklist.
     *
     * @param task Task to be removed.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Gets task based on the given {@code index}.
     *
     * @param index Index of the task to be retrieved (starting from 0).
     * @return Task at the given index.
     * @throws IndexOutOfBoundsException If given index is out of bounds.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Gets all tasks from the internal tasklist.
     *
     * @return A copy of the list of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns number of tasks in the internal tasklist.
     *
     * @return Number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }
}
