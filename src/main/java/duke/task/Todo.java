package duke.task;

/**
 * Represents a task type as Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo Class.
     *
     * @param description String the content of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of a task containing status icon and description.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
