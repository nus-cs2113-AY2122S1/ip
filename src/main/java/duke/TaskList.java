package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * This class hold all the tasks that the user has added. This class is also responsible for
 * handling the manipulation of the task list like adding, deleting and viewing the tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Sets up the class by assigning a list of task that is gotten from the storage object
     *
     * @param tasks the list of task that is extracted from the file that is gotten from the storage object
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the task list
     *
     * @return the number of tasks that is stored in tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all the task in an ArrayList to be written into the file used to store task data permanently
     *
     * @return tasks as an ArrayList so it can be written to the file storing file data
     */
    public ArrayList<Task> saveTasks() {
        return tasks;
    }

    /**
     * Check if the task list does not contain any task
     *
     * @return true if the task list is empty else it returns false
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the task at the specified index that is in the task list. If the index is too large,
     * an exception will be thrown, telling the user that the task at the index does not exist
     *
     * @param index Index of the task in the task list that the user wants to retrieve
     * @return A task if the index exist in the task list else an exception will be returned
     */
    public Task getTask(int index) {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! The list does not have that many task ><");
        }
    }

    /**
     * Adds a task into the existing list of task
     *
     * @param task Task that we want to add to the task list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Mark the task at the specified index in the task list as completed. If the task at the index
     * does no exist, an exception will be thrown
     *
     * @param taskIndex the index of the task to be marked as done
     * @return the task that is marked done
     */
    public Task doneTask(int taskIndex) {
        Task task = getTask(taskIndex);
        task.setDone();
        return task;
    }

    /**
     * Remove the task at the specific index from the task list. If the task at the index
     * does no exist, an exception will be thrown
     *
     * @param taskIndex the index of the task to be removed from the task list
     * @return the task that has been removed from the task list
     */
    public Task deleteTask(int taskIndex) {
        try {
            return tasks.remove(taskIndex);
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("☹ OOPS!!! The list does not have that many task ><");
        }
    }

    /**
     * Return a task list that contains task that only stores task that contains words in their description that
     * matches the input string. If no task description matches the string, an empty ArrayList will be returned
     *
     * @param word Word which we want the description of the task to have
     * @return a TaskList of tasks that contains the specified word
     */
    public TaskList getFilteredTask(String word) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for(Task t: tasks) {
            if (t.getDescription().contains(word)) {
                filteredTasks.add(t);
            }
        }
        return new TaskList(filteredTasks);
    }
}

