package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The class that stores the ArrayList of tasks
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Get the size of list
     * @return the number of tasks
     */
    public int getListSize() {
        return taskList.size();
    }

    /**
     * Get the task at a certain index
     * @param index the index of task to get
     * @return the task at the index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Get the entire ArrayList of tasks
     * @return the ArrayList of tasks
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Overwrites the ArrayList with another ArrayList
     * @param taskList the new ArrayList
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new task
     * @param task task to add
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Mark a task as done at a certain index
     * @param index the index of task to mark as done
     */
    public void markDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Delete a task at a certain index
     * @param index the index of task to delete
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Finds tasks that contains a certain keyword
     * @param toFind the keyword to find
     * @return ArrayList of tasks that contains keyword
     */
    public ArrayList<Task> findTask(String toFind) {
        int size = this.getListSize();
        ArrayList<Task> tasksFound = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Task currTask = this.getTask(i);

            if (currTask.getDescription().contains(toFind)) {
                tasksFound.add(currTask);
            }
        }

        return tasksFound;
    }
}
