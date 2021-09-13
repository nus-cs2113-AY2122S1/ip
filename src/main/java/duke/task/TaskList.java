package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.ui.Ui;

/**
 * This class manages the list of tasks.
 *
 * @author richwill28
 */
public class TaskList {
    /** The list of tasks */
    private List<Task> taskList;

    /**
     * The constructor method. Initializes the
     * list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds new task to the list.
     *
     * @param task New task.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the current size of the list.
     *
     * @return Size of the list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the task object at a particular
     * index.
     *
     * @param index Index of the task.
     * @return Task object.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Marks a task at a particular index as done.
     *
     * @param index Index of the task.
     */
    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the string representation of TaskList.
     *
     * @return The string representation of TaskList.
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            s += Ui.PADDING + (i + 1) + "." + task + System.lineSeparator();
        }
        return s;
    }
}
