package duke;

/**
 * Represents a <code>Task</code> to be done.
 */
public class Todo extends Task {
    private static final String TODO_ICON = "[T]";

    /**
     * Instantiates a <code>Todo</code> <code>Task</code>.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the task to <code>String</code>.
     *
     * @return Formatted task to user-friendly output.
     */
    @Override
    public String toString() {
        return TODO_ICON + super.toString();
    }
}
