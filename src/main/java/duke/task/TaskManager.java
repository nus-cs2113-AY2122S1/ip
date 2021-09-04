package duke.task;

import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;
import java.util.ArrayList;

public class TaskManager {

    /** List of tasks to manage */
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Create task manager.
     */
    public TaskManager() {
    }

    /**
     * Checks if task index is within task list size.
     *
     * @param taskIndex Index of task
     * @return Returns true if index is within task list size, otherwise false.
     */
    private boolean hasTaskIndex(int taskIndex) {
        return (taskIndex >= 0) && (taskIndex < getTotalTasks());
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
     * @throws InvalidTaskIndexException Task index provided is not within task list size.
     * @throws TaskListEmptyException Task list is empty.
     */
    public void markTaskAsDone(int taskIndex) throws InvalidTaskIndexException, TaskListEmptyException {
        if (!hasTaskIndex(taskIndex)) {
            throw new InvalidTaskIndexException();
        } else if (getTotalTasks() == 0) {
            throw new TaskListEmptyException();
        }

        getTask(taskIndex).markAsDone();
    }
}
