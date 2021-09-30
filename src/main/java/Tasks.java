import java.util.ArrayList;

/**
 * Represents the list of Tasks made by the user.
 */
public class Tasks {
    private ArrayList<Task> tasks;

    /**
     * Constructor for the list of tasks
     */
    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks
     * @param task The task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the number of tasks in the list of tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the corresponding index in the list of tasks
     * @param index The index of the task to be returned
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }
    /**
     * Prints all existing tasks in the list of tasks
     */
    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void delete(Task task) {
        tasks.remove(task);
    }

    /**
     * Returns the number of incomplete tasks in the list of tasks
     */
    public int tasksIncomplete() {
        int incompleteTasks = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).complete) {
                incompleteTasks ++;
            }
        }
        return incompleteTasks;
    }
}

