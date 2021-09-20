package duke.task;

/**
 * Inherits from Task, and represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with a description.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Provides a String representation of Todo.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
