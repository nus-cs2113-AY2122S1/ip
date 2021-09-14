package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list of tasks
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a task to the list of tasks, prints completion message via ui
     *
     * @param task the task to be added
     * @param ui   Ui class instance to print completion message
     */
    public void addTask(Task task, Ui ui) {
        addTask(task);
        ui.printAddTaskMessage(task, tasks.size());
    }

    /**
     * Deletes the task at the specified index
     *
     * @param taskIndex the index of the task to be deleted
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        if (taskIndex <= -1) {
            throw new DukeException("Task index must be greater than 0.");
        } else if (taskIndex >= tasks.size()) {
            throw new DukeException("Task does not exist.");
        }
        return tasks.remove(taskIndex);
    }

    /**
     * Deletes the task at the specified index, prints completion message via ui
     *
     * @param taskIndex the index of the task to be deleted
     * @param ui        Ui class instance to print completion message
     */
    public void deleteTask(int taskIndex, Ui ui) throws DukeException {
        Task deletedTask = deleteTask(taskIndex);
        ui.printDeleteTaskMessage(deletedTask, tasks.size());
    }

    /**
     * Deletes all tasks
     */
    public void deleteAllTasks() {
        tasks.clear();
    }

    /**
     * Prints a list of all tasks in taskList
     */
    public void printTasks(Ui ui) {
        ui.printAllTasks(tasks);
    }

    /**
     * Marks the task at the specified index as completed
     *
     * @param taskIndex the index of the task to be marked as completed
     */
    public void completeTask(int taskIndex) throws DukeException {
        if (taskIndex <= -1) {
            throw new DukeException("Task index must be greater than 0.");
        } else if (taskIndex >= tasks.size()) {
            throw new DukeException("Task does not exist.");
        }
        tasks.get(taskIndex).setCompleted();
    }

    /**
     * Marks the task at the specified index as completed, prints completion message via ui
     *
     * @param taskIndex the index of the task to be marked as completed
     * @param ui        Ui class instance to print completion message
     */
    public void completeTask(int taskIndex, Ui ui) throws DukeException {
        completeTask(taskIndex);
        ui.printCompleteTaskMessage(tasks.get(taskIndex));
    }

    /**
     * Creates a string containing the task information for all Tasks in TaskList
     *
     * @return string containing the task information for all Tasks in TaskList, separated by lineSeparators
     */
    public String getStorageString() {
        String storageString = "";
        for (Task task : tasks) {
            storageString = storageString + task.getStorageString() + System.lineSeparator();
        }
        return storageString;
    }
}
