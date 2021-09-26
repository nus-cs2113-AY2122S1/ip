package duke.system;

import duke.task.Task;
import java.util.ArrayList;

/**
 * A component that manipulates tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * TaskList constructor for a given task list.
     * @param tasks arraylist that holds all the tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * TaskList constructor for a new empty list.
     * Only used when the local file is corrupted.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Mark a task as done.
     * @param currentTaskIndex the index of the selected task
     * @throws IndexOutOfBoundsException if the index given is invalid
     */
    public void markTaskDone(int currentTaskIndex) throws IndexOutOfBoundsException {
        this.tasks.get(currentTaskIndex).markAsDone();
    }

    /**
     * Get the full information of a task.
     * @param currentTaskIndex the index of the selected task
     * @return the full information of the task as a String.
     */
    public String getTaskDetails(int currentTaskIndex) {
        return this.tasks.get(currentTaskIndex).toString();
    }

    /**
     * Get the list of tasks in the ArrayList format.
     * @return a ArrayList containing all the tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Add a new task to the list.
     * @param newTask the new task to be added
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Delete a task from the list.
     * @param currentTaskIndex the index of the selected task
     */
    public void deleteTask(int currentTaskIndex) {
        tasks.remove(currentTaskIndex);
    }

    /**
     * Get the number of tasks in the list.
     * @return the number of tasks as an integer.
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    public ArrayList<Task> getRelevantTaskList(String keywords) {
        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            if (currentTask.containsKeywords(keywords)) {
                relevantTasks.add(currentTask);
            }
        }
        return relevantTasks;
    }


}
