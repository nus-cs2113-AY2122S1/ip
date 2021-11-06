package task;

/**
 * Todo task. Includes whether it is completed and a description.
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts todo into a string for displaying to user.
     * @return todo display string.
     */
    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + super.toString();
    }

    /**
     * Converts a todo into a string for saving in a file.
     * @return todo file string.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
