package duke.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import duke.exception.MissingArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a list of tasks for Duke. A TaskList object tracks tasks recorded.
 */
public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a todo task to the list of tasks
     *
     * @param description Description of the task
     * @return Todo object added
     * @throws MissingArgumentException If description is empty
     */
    public Todo addTodo(String description) throws MissingArgumentException {
        if (description == "") {
            throw new MissingArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(description);
        tasks.add(todo);
        return todo;
    }

    /**
     * Adds a deadline task to the list of tasks
     *
     * @param description Description of the task
     * @param by deadline of the task
     * @return Deadline object added
     * @throws MissingArgumentException If description is empty or deadline is missing/empty
     */
    public Deadline addDeadline(String description, String by) throws MissingArgumentException {
        if (description == "") {
            throw new MissingArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (by == null || by.isEmpty()) {
            throw new MissingArgumentException("☹ OOPS!!! The date of a deadline cannot be empty.");
        }
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        return deadline;
    }

    /**
     * Adds an event task to the list of tasks
     *
     * @param description Description of the task
     * @param at date of occurrence
     * @return Event object added
     * @throws MissingArgumentException If description is empty or date of occurrence is missing/empty
     */
    public Event addEvent(String description, String at) throws MissingArgumentException {
        if (description == "") {
            throw new MissingArgumentException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (at == null || at.isEmpty()) {
            throw new MissingArgumentException("☹ OOPS!!! The date of an event cannot be empty.");
        }
        Event event = new Event(description, at);
        tasks.add(event);
        return event;
    }

    /**
     * Marks a task specified by index as done
     *
     * @param index Index location of the task in the list of tasks, counting from 1
     * @return Task marked done
     */
    public Task setTaskDone(int index) {
        Task task = getTask(index);
        task.markAsDone();
        return task;
    }

    /**
     * Deletes a task specified by index
     *
     * @param index Index location of the task in the list of tasks, counting from 1
     * @return Deleted task
     */
    public Task deleteTask(int index) {
        Task task = getTask(index);
        tasks.remove(index - 1);
        return task;
    }

    /**
     * Searches for tasks that contains the search term in their description
     *
     * @param search Search term
     * @return list of tasks matching search term
     */
    public List<Task> findTasks(String search) {
        return this.tasks.stream().filter(item -> item.getDescription().contains(search))
                .collect(Collectors.toList());
    }

    /**
     * Gets a task specified by index
     *
     * @param index Index location of the task in the list of tasks, counting from 1
     * @return Task object at index - 1
     * @throws IndexOutOfBoundsException If specified index is out of the range of the list
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Gets all tasks in the list of tasks
     *
     * @return List of task objects
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the length of the list of tasks
     *
     * @return Length of tasks list
     */
    public int size() {
        return tasks.size();
    }

}
