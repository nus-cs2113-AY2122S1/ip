package duke.data.task;

import duke.logic.exceptions.TaskAlreadyDoneException;
import duke.logic.exceptions.TaskListEmptyException;
import duke.logic.exceptions.TaskNumOutOfBoundsException;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList of Tasks
 */
public class TaskList {
    private final ArrayList<Task> internalTasks = new ArrayList<>();

    /**
     * Constructs empty list
     */
    public TaskList() {
    }

    /**
     * Constructs task list from list of Tasks (from storage)
     */
    public TaskList(List<Task> tasks) {
       internalTasks.addAll(tasks);
    }

    /**
     * Converts task number to index number (difference is a result of 0-base vs 1-base)
     */
    public int convertToIndexNum(int taskNum) {
        return taskNum - 1;
    }

    /**
     * Adds task to list
     */
    public void addTask(Task task) {
      internalTasks.add(task);
    }

    /**
     * Deletes task in list, given the task number
     */
    public void deleteTask(int taskNum) throws TaskListEmptyException, TaskNumOutOfBoundsException {
        if (internalTasks.isEmpty()) {
            throw new TaskListEmptyException();
        }

        try {
            internalTasks.remove(convertToIndexNum(taskNum));
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumOutOfBoundsException();
        }

    }

    /**
     * Marks task as done, given the task number
     */
    public void markTaskAsDone(int taskNum) throws TaskListEmptyException, TaskNumOutOfBoundsException,
            TaskAlreadyDoneException {

        if (internalTasks.isEmpty()) {
            throw new TaskListEmptyException();
        }

        try {
            internalTasks.get(convertToIndexNum(taskNum)).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumOutOfBoundsException();
        }
    }

    /**
     * Returns the ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.internalTasks;
    }

    /**
     * Returns the task at specific task number
     */
    public Task getTaskAtNum(int taskNum) throws TaskNumOutOfBoundsException {
        try {
            return this.internalTasks.get(convertToIndexNum(taskNum));
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumOutOfBoundsException();
        }
    }

    /**
     * Returns size of tasks
     */
    public int getNumTasks() {
        return this.internalTasks.size();
    }

    /**
     * Returns all the tasks in string form
     */
    public String getStringOfAllTasks() {
        String stringOfAllTasks = Ui.EMPTY;
        int taskNum = 1;
        for (Task task : internalTasks) {
            stringOfAllTasks = stringOfAllTasks + taskNum + "." + task.toString() + Ui.LS;
            taskNum++;
        }
        return stringOfAllTasks.trim();
    }
}
