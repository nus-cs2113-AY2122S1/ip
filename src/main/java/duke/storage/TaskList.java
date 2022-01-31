package duke.storage;

import duke.DukeException;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the list of tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private TaskStorage storage;
    private static TaskList instance = null;

    /**
     * Constructor method for <code>TaskList</code>
     *
     * @throws DukeException if the tasks in the memory file cannot be read
     */
    public TaskList() throws DukeException {
        try {
            storage = new TaskStorage();
            taskList = storage.readTasksFromMemory();
        } catch (IOException e) {
            taskList = new ArrayList<>();
            throw new DukeException("Cannot read tasks from memory!");
        }

    }

    /**
     * Gets the instance of the <code>TaskList</code>
     * By doing this, we avoid initializing an instance outside the class itself
     *
     * @return The instance of the <code>TaskList</code>
     * @throws DukeException if the tasks in the memory file cannot be read
     */
    public static TaskList getInstance() throws DukeException{
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    /**
     * Adds a task into the task list and write the task list into the memory
     *
     * @param task The task to be added to the list
     * @throws IOException if the tasks cannot be written into the memory
     */
    public void add(Task task) throws IOException {
        taskList.add(task);
        storage.writeTasksToMemory(taskList);
    }

    /**
     * Deletes a task from the list and from the memory
     *
     * @param taskNumber the task number in the list to be deleted
     * @return the deleted task, to be used later in the program
     * @throws IOException if the task cannot be deleted from the memory
     */
    public Task delete(int taskNumber) throws IOException {
        Task removedTask = taskList.remove(taskNumber - 1);
        storage.writeTasksToMemory(taskList);
        return removedTask;
    }

    /**
     * Sets a task as done in the list and in the memory
     *
     * @param taskNumber the task number in the list to be set as done
     * @throws IOException if the task cannot be written to the memory
     * @throws IndexOutOfBoundsException if the task number does not exist
     */
    public void setDone(int taskNumber) throws IOException, IndexOutOfBoundsException {
        taskList.get(taskNumber - 1).setDone();
        storage.writeTasksToMemory(taskList);
    }

    /**
     * Gets the number of tasks currently in the list
     *
     * @return the number of tasks in the list
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Gets the string representation of a task in the task list
     *
     * @param taskIndex the task index (i.e 0 to N) in the task list array
     * @return the string representation of the task
     * @throws DukeException if the task index is not within the bound of the task list array
     */
    public String getTaskInfo(int taskIndex) throws DukeException{
        try {
            return taskList.get(taskIndex).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number not exist!");
        }
    }

    /**
     * Gets a copy of the current task list array
     *
     * @return an array of the current task list
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
