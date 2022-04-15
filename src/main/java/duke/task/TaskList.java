package duke.task;

import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;
import java.util.ArrayList;

public class TaskList {

    /**
     * List of tasks to manage
     */
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Creates task list.
     */
    public TaskList() {
    }

    /**
     * Checks if task index is within task list size.
     *
     * @param taskIndex Index of task.
     * @return Boolean indicating if index is within task list size.
     */
    private boolean hasTaskIndex(int taskIndex) {
        boolean greaterThanZero = taskIndex >= 0;
        boolean lessThanMaxTasks = taskIndex < getTotalTasks();
        return greaterThanZero && lessThanMaxTasks;
    }

    /**
     * Returns the total number of tasks in the task list.
     *
     * @return Number of total tasks in the task list.
     */
    public int getTotalTasks() {
        return taskList.size();
    }

    /**
     * Get the task with the specified index from the task list.
     *
     * @param taskIndex Index of the task in the task list.
     * @return The task according index provided.
     * @throws TaskListEmptyException    Task list is empty.
     * @throws InvalidTaskIndexException Task index provided is not within task list size.
     */
    public Task getTask(int taskIndex) throws TaskListEmptyException, InvalidTaskIndexException {
        if (getTotalTasks() == 0) {
            throw new TaskListEmptyException();
        } else if (!hasTaskIndex(taskIndex)) {
            throw new InvalidTaskIndexException();
        }

        return this.taskList.get(taskIndex);
    }

    /**
     * Add a new task into the task list.
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
     * @throws TaskListEmptyException    Task list is empty.
     */
    public void markTaskAsDone(int taskIndex) throws InvalidTaskIndexException, TaskListEmptyException {
        if (!hasTaskIndex(taskIndex)) {
            throw new InvalidTaskIndexException();
        } else if (getTotalTasks() == 0) {
            throw new TaskListEmptyException();
        }

        getTask(taskIndex).markAsDone();
    }

    /**
     * Delete a task from the task list.
     *
     * @param taskIndex Index of task.
     * @throws InvalidTaskIndexException Task index provided is not within task list size.
     * @throws TaskListEmptyException    Task list is empty.
     */
    public void deleteTask(int taskIndex) throws InvalidTaskIndexException, TaskListEmptyException {
        if (!hasTaskIndex(taskIndex)) {
            throw new InvalidTaskIndexException();
        } else if (getTotalTasks() == 0) {
            throw new TaskListEmptyException();
        }

        this.taskList.remove(taskIndex);
    }
}
