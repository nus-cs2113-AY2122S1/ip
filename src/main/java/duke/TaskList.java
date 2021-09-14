package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns the number of Tasks in TaskList
     *
     * @return the number of Tasks in TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds the task to the list of tasks
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index
     *
     * @param taskIndex the index of the task to be deleted
     * @return the Task that was deleted
     * @throws DukeException if taskIndex is < 0 or if there is no task at the specified index
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        if (taskIndex < 0) {
            throw new DukeException("Task index must be greater than 0.");
        } else if (taskIndex >= tasks.size()) {
            throw new DukeException("Task does not exist.");
        }
        return tasks.remove(taskIndex);
    }

    /**
     * Deletes all tasks in TaskList
     */
    public void deleteAllTasks() {
        tasks.clear();
    }

    /**
     * Marks the task at the specified index as completed
     *
     * @param taskIndex the index of the task to be marked as completed
     * @return the Task that was marked as completed
     * @throws DukeException if taskIndex < 0 or if there is no task at the specified index
     */
    public Task completeTask(int taskIndex) throws DukeException {
        if (taskIndex < 0) {
            throw new DukeException("Task index must be greater than 0.");
        } else if (taskIndex >= tasks.size()) {
            throw new DukeException("Task does not exist.");
        }
        tasks.get(taskIndex).setCompleted();
        return tasks.get(taskIndex);
    }

    /**
     * Creates a string containing the task information for all Tasks in TaskList for printing to terminal
     *
     * @return String containing the task information for all Tasks in TaskList for printing to terminal
     */
    public String getMessageString() {
        return Output.getTaskListMessage(tasks);
    }

    /**
     * Creates a string containing the task information for all Tasks in TaskList for storage
     *
     * @return String containing the task information for all Tasks in TaskList, separated by lineSeparators
     */
    public String getStorageString() {
        String storageString = "";
        for (Task task : tasks) {
            storageString = storageString + task.getStorageString() + System.lineSeparator();
        }
        return storageString;
    }
}
