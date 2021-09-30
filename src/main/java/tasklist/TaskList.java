package tasklist;

import tasks.Task;
import java.util.ArrayList;

/**
 * Represents the interactions with the task list for data storage.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds task to task list.
     *
     * @param task Task to be added to the task list.
     */
    public void addTaskToList(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from task list.
     *
     * @param task Task to be removed from the task list.
     */
    public void removeTaskFromList(Task task) {
        tasks.remove(task);
    }

    /**
     * Clears all tasks from the task list.
     */
    public static void clearAllTasksFromList() {
        tasks.clear();
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Returns a certain task from the task list.
     *
     * @param index Index of the task to be returned.
     * @return Task to be returned.
     */
    public Task getTaskFromList(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the task list.
     *
     * @return Task list.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
