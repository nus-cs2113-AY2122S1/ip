package tasklist;

import task.Task;

import java.util.ArrayList;

/**
 * A class that stores an ArrayList of tasks and the related methods for the ArrayList.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int numberOfTasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    /**
     * @return Returns the number of tasks in the list produced by execution of FindCommand.
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTask(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        Task.setTotalTasks(Task.getTotalTasks() + 1);
    }

    /**
     * Adds existing tasks to a new list when FindCommand is executed.
     * This is to separate from the usual addTask method which increases
     * the total number of tasks in the main ArrayList.
     *
     * @param task The task that needs to be printed after executing FindCommand.
     */
    public void addTaskForFindCommand(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        tasks.remove(index);
        Task.setTotalTasks(Task.getTotalTasks() - 1);
    }

    public Task getTask(int index) throws IndexOutOfBoundsException{
        return tasks.get(index);
    }
}
