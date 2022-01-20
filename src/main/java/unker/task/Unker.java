package unker.task;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import unker.task.storage.TasksFile;
import unker.task.storage.TasksFileException;

/**
 * This class manages all the {@link Task} in the program.
 */
public class Unker {

    private List<Task> tasks;
    private static Unker UNKER_INSTANCE;
    private final TasksFile tasksFile;

    private Unker(Path directory, String fileName)  {
        this.tasksFile = new TasksFile(directory, fileName);
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to be managed by Unker.
     *
     * @param task The task to be added.
     * @throws TasksFileException When the update is unable to be saved.
     */
    public void addTask(Task task) throws TasksFileException {
        tasks.add(task);
        tasksFile.saveDataFile(tasks);
    }

    /**
     * Gets the full list of tasks managed by Unker.
     * 
     * @return The list of tasks inside this Unker.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets a task by a specific index number.
     *
     * @param index The index number for the task.
     * @return The task based on the index number.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task based on the index.
     * 
     * @param index The index of the task to remove.
     * @return The task that has been removed.
     * @throws TasksFileException When the update is unable to be saved.
     */
    public Task removeTask(int index) throws TasksFileException {
        Task task = tasks.remove(index);
        saveData();
        return task;
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
     * Save the tasks into the disk.
     * 
     * @throws TasksFileException When the file is inaccessible.
     */
    public void saveData() throws TasksFileException {
        tasksFile.saveDataFile(tasks);
    }
    
    /**
     * Save the tasks into the disk.
     *
     * @throws TasksFileException When the file is inaccessible.
     */
    public void loadData() throws TasksFileException {
        this.tasks = tasksFile.loadDataFile();
    }

    /**
     * Returns a singleton instance of Unker.
     *
     * @return A singleton instance of Unker
     */
    public static Unker getUnkerInstance()  {
        if (UNKER_INSTANCE == null) {
            UNKER_INSTANCE = new Unker(Path.of(System.getProperty("user.dir"), "data"), "unker.csv");
        }
        return UNKER_INSTANCE;
    }

}
