package unker;

import java.util.ArrayList;
import unker.task.Task;

/**
 * This class manages all the {@link Task} in the program.
 */
public class Unker {

    private final ArrayList<Task> tasks;
    private static final Unker UNKER_INSTANCE = new Unker();

    private Unker() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a task to be managed by Unker.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Get a task by a specific index number.
     *
     * @param index The index number for the task.
     * @return The task based on the index number.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the total number of tasks (both completed and pending)
     *
     * @return the total number of tasks (both completed and pending).
     */
    public int getTotalTasks() {
        return tasks.size();
    }

    /**
     * Checks if there are any tasks added.
     *
     * @return Returns true if there are no tasks added.
     */
    public boolean isTasksEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a singleton instance of Unker.
     *
     * @return A singleton instance of Unker
     */
    public static Unker getUnkerInstance() {
        return UNKER_INSTANCE;
    }

}
