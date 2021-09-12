package unker.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import unker.task.storage.TasksFile;
import unker.util.StringUtil;

/**
 * This class manages all the {@link Task} in the program.
 */
public class Unker {

    private final List<Task> tasks;
    private static Unker UNKER_INSTANCE;
    private final TasksFile tasksFile;

    private Unker() throws IOException {
        this.tasksFile = new TasksFile(Path.of(System.getProperty("user.dir"), "data"), "unker.csv");
        this.tasks = tasksFile.loadDataFile();
    }

    /**
     * Add a task to be managed by Unker.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        saveData();
    }
    
    

    public List<Task> getTasks() {
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
    
    public boolean saveData() {
        try {
            tasksFile.saveDataFile(tasks);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Returns a singleton instance of Unker.
     *
     * @return A singleton instance of Unker
     */
    public static Unker getUnkerInstance() throws IOException {
        if (UNKER_INSTANCE == null) {
            UNKER_INSTANCE = new Unker();
        }
        return UNKER_INSTANCE;
    }

}
