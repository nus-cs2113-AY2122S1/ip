package duke;

import java.util.ArrayList;

/**
 * This class deals with operations on the list storing all the tasks
 */
public class TaskList {

    protected static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets the number of tasks in the task list
     *
     * @return number of tasks in the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Accesses a specific task at the given index
     *
     * @param index index of the task that the user wants to access
     * @return the task at the specified index in the list
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Add a todo task into the list of tasks
     *
     * @param description description of the todo task added
     */
    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    /**
     * Add a deadline into the list of tasks
     *
     * @param description description of the task added
     * @param by deadline of the task added
     */
    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    /**
     * Add an event into the list of tasks
     *
     * @param description description of the event added
     * @param at time where the event added is going to happen
     */
    public void addEvent(String description, String at) {
        tasks.add(new Event(description, at));
    }

    /**
     * Mark a specific task as done
     *
     * @param index index of the completed task
     */
    public void completeTask(int index) {
        tasks.get(index).completeTask();
    }

    /**
     * Delete a specific task
     *
     * @param index index of the task that the user wants to delete
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Lists out all tasks currently in the list
     */
    public void listTask() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).printTask(i + 1);
        }
    }

}
