package task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the class that performs operations on the task list.
 */
public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void setTaskList(ArrayList<Task> taskList) {
        if (taskList != null) {
            this.taskList = taskList;
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    /**
     * Loads the saved task list onto the task list.
     * @param savedTaskList the saved task list
     */
    public TaskManager(ArrayList<Task> savedTaskList) {
        taskList.addAll(savedTaskList);
    }

    /**
     * Gets the sorted task list.
     * Events and deadlines are sorted by date.
     * Todos are appended to the list after that.
     */
    public ArrayList<Task> getSortedList() {
        sortTaskList();
        return getTaskList();
    }

    private void sortTaskList() {
        ArrayList<TaskWithDate> taskWithDateList = filterTasksWithDateFromTaskList();
        sortByDate(taskWithDateList);
        ArrayList<Task> sortedTaskList = new ArrayList<>(taskWithDateList);
        for (Task t:taskList) {
            if (t instanceof Todo) {
                sortedTaskList.add(t);
            }
        }
        taskList = new ArrayList<>(sortedTaskList);
    }

    private void sortByDate(ArrayList<TaskWithDate> taskWithDateList) {
        int n = taskWithDateList.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (taskWithDateList.get(j).isAfter(taskWithDateList.get(j + 1))) {
                    Collections.swap(taskWithDateList, j, j + 1);
                }
            }
        }
    }

    private ArrayList<TaskWithDate> filterTasksWithDateFromTaskList() {
        ArrayList<TaskWithDate> taskWithDateList = new ArrayList<>();
        for (Task t : taskList) {
            if (t instanceof TaskWithDate) {
                taskWithDateList.add((TaskWithDate) t);
            }
        }
        return taskWithDateList;
    }

    /**
     * Adds a task to the task list.
     * @param t task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes a task from the task list.
     * @param t task to be deleted.
     */
    public void deleteTask(Task t) {
        taskList.remove(t);
    }

    /**
     * Marks a task from the task list as completed.
     * @param index of the task to be deleted.
     */
    public void markTaskAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Clears the task list.
     */
    public void clearHistory() {
        taskList.clear();
    }
}
