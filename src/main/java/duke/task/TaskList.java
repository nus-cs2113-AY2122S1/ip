package duke.task;

import java.util.ArrayList;

/**
 * Manage the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int numOfTasks = 0;

    /**
     * Creates a TaskList object
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add a Todo Task into the task list
     *
     * @param description The description of the Todo task
     * @return the new Task object created
     */
    public Task addTodo(String description) {
        Task newTask = new Todo(description);
        tasks.add(newTask);
        numOfTasks++;
        return newTask;
    }

    /**
     * Add a Deadline Task into the task list.
     *
     * @param description The description of the Deadline task.
     * @param timeField The due date of the Deadline task.
     * @return the new Task object created.
     */
    public Task addDeadline(String description, String timeField) {
        Task newTask = new Deadline(description, timeField);
        tasks.add(newTask);
        numOfTasks++;
        return newTask;
    }

    /**
     * Add a Event Task into the task list.
     *
     * @param description The description of the Event task.
     * @param timeField The due date of the Event task.
     * @return the new Task object created.
     */
    public Task addEvent(String description, String timeField) {
        Task newTask = new Event(description, timeField);
        tasks.add(newTask);
        numOfTasks++;
        return newTask;
    }

    /**
     * Mark a task as done
     *
     * @param index The item number of the task to be marked done.
     * @return The Task object that is marked done.
     */
    public Task markDone(int index) {
        Task task = tasks.get(index - 1);
        task.setDone();
        return task;
    }

    /**
     * Delete a task from the task list.
     *
     * @param index The item number of the task to be deleted.
     * @return The Task object that is deleted.
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        numOfTasks--;
        return task;
    }

    /**
     * Get the ArrayList of tasks.
     *
     * @return the ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Get the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getNumOfTasks() {
        return numOfTasks;
    }
}
