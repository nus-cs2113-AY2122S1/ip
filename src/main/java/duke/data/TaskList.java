package duke.data;

import duke.data.task.Task;

import java.util.ArrayList;

/**
 * Represents the entire task list. Contains the data of the task list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Clears all the tasks from the task list.
     */
    public void clearList() {
        tasks.clear();
    }

    /**
     * Returns the task list.
     *
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the specified task by the given index.
     *
     * @param index the index representing the task in the task list
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the index of the given task.
     */
    public int getIndex(Task task) {
        return tasks.indexOf(task);
    }
}
