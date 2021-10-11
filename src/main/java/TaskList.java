import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList() {
    }

    /**
     * Adds task.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the last task in tasks.
     *
     * @return the last task.
     */
    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Deletes the task by its index.
     *
     * @param index the index of the task to be deleted.
     */
    public void deleteItemFromIndex(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the length of tasks.
     *
     * @return length of tasks.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param index index of task in tasks.
     * @return returns the task at that index.
     */
    public Task getItemFromIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the whole tasks.
     *
     * @return tasks.
     */
    public ArrayList<Task> getList() {
        return tasks;
    }


}
