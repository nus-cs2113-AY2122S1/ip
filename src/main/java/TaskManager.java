import java.util.ArrayList;

public class TaskManager {

    /** List of tasks to manage */
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Create task manager.
     */
    public TaskManager() {
    }

    public int getTotalTasks() {
        return taskList.size();
    }

    /**
     * Get the task with the specified index from the task list.
     *
     * @param taskIndex Index of the task in the task list.
     * @return Task.
     */
    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Add task into task list.
     *
     * @param newTask Task to be added.
     */
    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    /**
     * Mark the task in the specified position within the task list as done.
     *
     * @param taskIndex Index of task.
     */
    public void markTaskAsDone(int taskIndex) {
        taskList.get(taskIndex).markAsDone();
    }
}
