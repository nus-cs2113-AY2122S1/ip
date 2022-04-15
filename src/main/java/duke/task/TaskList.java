package duke.task;

/**
 * Class that manages operations of adding tasks to the list.
 */
public class TaskList {

    /**
     * Returns a new Event task.
     *
     * @param description Description of the Event object.
     * @param at Time that the event takes place.
     * @return Task object corresponding to the Event.
     */
    public static Task addEvent(String description, String at) {
        return new Event(description, at);
    }

    /**
     * Returns a new Deadline task.
     *
     * @param description Description of the Deadline object.
     * @param by Time by which the task should be completed.
     * @return Task object corresponding to the Deadline.
     */
    public static Task addDeadline(String description, String by) {
        return new Deadline(description, by);
    }

    /**
     * Returns a new Todo task.
     *
     * @param description Description of the Todo object.
     * @return Task object corresponding to the Todo.
     */
    public static Task addTodo(String description) {
        return new Todo(description);
    }
}
