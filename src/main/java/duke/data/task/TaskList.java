package duke.data.task;

import duke.logic.exceptions.TaskAlreadyDoneException;
import duke.logic.exceptions.TaskListEmptyException;
import duke.logic.exceptions.TaskNumOutOfBoundsException;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * Gets task number of task (difference from index is a result of 0-base vs 1-base)
     */
    public int getTaskNum(Task task) {
        return internalTasks.indexOf(task) + 1;
    }


    /**
     * Adds task to list
     */
    public void addTask(Task task) {
      internalTasks.add(task);
    }

    /**
     * Deletes task in list, given the task number
     *
     * @throws TaskListEmptyException If current task list is empty
     * @throws TaskNumOutOfBoundsException If taskNum is not within the indexes that the TaskList has
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
     *
     * @param taskNum Task number
     * @throws TaskListEmptyException If current task list is empty
     * @throws TaskNumOutOfBoundsException If taskNum is not within the indexes that the TaskList has
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

    /** Returns the ArrayList of tasks*/
    public ArrayList<Task> getTasks() {
        return this.internalTasks;
    }

    /**
     * Returns the Task at the given task number
     *
     * @param taskNum Task number
     * @return Task at the given task number
     * @throws TaskListEmptyException If current task list is empty
     * @throws TaskNumOutOfBoundsException If taskNum is not within the indexes that the TaskList has
     */
    public Task getTaskAtNum(int taskNum) throws TaskListEmptyException, TaskNumOutOfBoundsException {
        if (this.internalTasks.isEmpty()) {
            throw new TaskListEmptyException();
        }
        try {
            return this.internalTasks.get(convertToIndexNum(taskNum));
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumOutOfBoundsException();
        }
    }

    /** Returns size of tasks */
    public int getNumTasks() {
        return this.internalTasks.size();
    }

    /** Returns true if TaskList is empty */
    public boolean isEmpty() {
        return this.internalTasks.isEmpty();
    }

    /** Returns all the tasks in string form */
    public String getStringOfAllTasks() {

        String stringOfAllTasks = Ui.EMPTY;
        for (Task task : internalTasks) {
            stringOfAllTasks = stringOfAllTasks + getTaskNum(task) + "." + task.toString() + Ui.LS;
        }
        return stringOfAllTasks.trim();
    }

    /**
     * Returns all the tasks in the given list in string form
     * @param listOfTasks List of tasks that is a subset of the internalTasks list
     */
    public String getStringOfTasksInList(List<Task> listOfTasks) {
        String stringOfAllTasks = Ui.EMPTY;
        for (Task task : listOfTasks) {
            stringOfAllTasks = stringOfAllTasks + getTaskNum(task) + "." + task.toString() + Ui.LS;
        }
        return stringOfAllTasks.trim();
    }

    /** Returns list of tasks that match search term */
    public List<Task> getListOfTasksWithMatchingTerm(String searchTerm) {
        return internalTasks.stream().filter(task -> task.description.toLowerCase().contains(searchTerm.toLowerCase())).collect(Collectors.toList());
    }

    /** Returns list of tasks that match search term in string form */
    public String getStringOfTasksWithMatchingTerm(String searchTerm) {
        List<Task> listOfTasks = getListOfTasksWithMatchingTerm(searchTerm);
        return getStringOfTasksInList(listOfTasks);
    }
}
