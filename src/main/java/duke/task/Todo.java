package duke.task;

/**
 * Represents a task that does not have any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Creates a task with the specified description.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task (consisting of the symbol for the task type, as well as the string
     * representation from the parent class).
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
