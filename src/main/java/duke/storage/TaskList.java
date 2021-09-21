package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.text.Text;

import java.util.ArrayList;

public class TaskList extends Text {

    protected ArrayList<Task> taskList;

    /**
     * A constructor to create a new task list.
     */
    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    /**
     * A constructor to open and read from existing task list.
     * @param openFile task list previously stored in text file.
     */
    public TaskList(TaskList openFile) {
        taskList = (ArrayList<Task>) openFile.taskList.clone();
    }

    /**
     * Returns the size of the task list.
     *
     * @return int value of size of task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * A getter to obtain a task from task list.
     *
     * @param index the index of a task.
     * @return Task object.
     * @throws DukeException exception thrown when index given is out of range of 0 and last index of task list.
     */
    public Task getTask(int index) throws DukeException {
        if (index < 0 || index > size() - 1) {
            throw new DukeException(INPUT_VALID_TASK);
        } else {
            return taskList.get(index);
        }
    }

    /**
     * Adds a new task into the task list.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a specific task from task list.
     *
     * @param index the index of a task.
     * @throws DukeException exception thrown when index given is out of range of 0 and last index of task list.
     */
    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index > size() - 1) {
            throw new DukeException(INPUT_VALID_TASK);
        } else {
            taskList.remove(index);
        }
    }

    /**
     * Marks a specific task from task list is done.
     *
     * @param index the index of a task.
     * @throws DukeException exception thrown when index given is out of range of 0 and last index of task list.
     */
    public void markTaskDone(int index) throws DukeException {
        if (index < 0 || index > size() - 1) {
            throw new DukeException(INPUT_VALID_TASK);
        } else {
            taskList.get(index).markDone();
        }
    }

    /**
     * Returns the list of all tasks currently in the task list.
     *
     * @return String of all tasks.
     */
    public String getList() {
        int taskNumber = 1;
        String listOfTask = "";
        for (Task task : taskList) {
            listOfTask = listOfTask.concat(taskNumber + "." + task.toString() + "\n");
            taskNumber++;
        }
        return listOfTask;
    }
}
