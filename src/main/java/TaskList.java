import java.util.ArrayList;

public class TaskList  {
    private static ArrayList<Task> tasks;

    /**
     * Constructor for TaskList, initializes the ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Singleton for the ArrayList.
     * @return Instance of the ArrayList.
     */
    public static ArrayList<Task> getTasksInstance() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        return tasks;
    }
}
