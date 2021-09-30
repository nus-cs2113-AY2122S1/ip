package tasks;

import java.util.ArrayList;

/**
 * Represents the entire task list. Contains all tasks of the task list.
 */
public class TaskList {
    private ArrayList<Task> allTasks;

    /**
     * Creates an empty task list
     */
    public TaskList() {
        allTasks = new ArrayList<>();
    }

    /**
     * Adds the given task into the task list.
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        allTasks.add(task);
    }

    /**
     * Deletes the task corresponding to the given task index from the task list.
     *
     * @param taskIndex task index of task to be deleted
     */
    public void deleteTask(int taskIndex) {
        allTasks.remove(taskIndex);
    }

    /**
     * Marks the task corresponding to the given task index from the task list as done.
     *
     * @param taskIndex task index of task to be marked as done
     */
    public void markTaskAsDone(int taskIndex) {
        allTasks.get(taskIndex).markAsDone();
    }


    public Task getTask(int taskIndex) {
        return allTasks.get(taskIndex);
    }

    public ArrayList<Task> getTaskList() {
        return allTasks;
    }

    public int getSize() {
        return allTasks.size();
    }

}
