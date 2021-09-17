package kate.tasklist;

import kate.task.Deadline;
import kate.task.Event;
import kate.task.Task;
import kate.task.ToDo;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Retrieves task size of the array list
     *
     * @return The task size in integer
     */
    public int getTaskSize() {
        return tasks.size();
    }

    /**
     * Retrieves a Task given a task index
     *
     * @param taskIndex Index of task supplied by user
     * @return Task object of the current task
     */
    public Task getCurrentTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public boolean isEmptyTask() {
        return tasks.isEmpty();
    }

    /**
     * Retrieves the task added most recently
     *
     * @return Most recently added Task object
     */
    public Task getMostRecentAddedTask() {
        int lastElementIndex = tasks.size() - 1;
        return tasks.get(lastElementIndex);
    }

    /**
     * Adds a todo task to be done
     *
     * @param taskDescription A general task that user wants to add
     */
    public void addToDo(String taskDescription) {
        tasks.add(new ToDo(taskDescription));
    }

    /**
     * Adds a todo task from file
     *
     * @param taskDescription Description of task
     * @param isDone          Task done status
     */
    public void addToDoFromFile(String taskDescription, boolean isDone) {
        tasks.add(new ToDo(taskDescription, isDone));
    }

    /**
     * Adds a task that has a deadline
     *
     * @param taskDescription description for task with deadline
     * @param deadline        deadline for a task
     */
    public void addDeadline(String taskDescription, String deadline) {
        tasks.add(new Deadline(taskDescription, deadline));
    }

    /**
     * Adds a task that has a deadline from file
     *
     * @param taskDescription Description of task with deadline
     * @param isDone          Task done status
     * @param deadline        Deadline of the task
     */
    public void addDeadlineFromFile(String taskDescription, boolean isDone, String deadline) {
        tasks.add(new Deadline(taskDescription, isDone, deadline));
    }

    /**
     * Adds a task that is an event
     *
     * @param taskDescription Description of task that is an event
     * @param timeFrame       Time frame for an event
     */
    public void addEvent(String taskDescription, String timeFrame) {
        tasks.add(new Event(taskDescription, timeFrame));
    }

    /**
     * Adds a task that is an event from file
     *
     * @param taskDescription Description of task
     * @param isDone          Task done status
     * @param event           Event time frame of the task
     */
    public void addEventFromFile(String taskDescription, boolean isDone, String event) {
        tasks.add(new Event(taskDescription, isDone, event));
    }

    /**
     * Delete a task specified by a task number
     *
     * @param deletedTask Task object to be deleted
     */
    public void deleteTask(Task deletedTask) {
        tasks.remove(deletedTask);
    }
}
