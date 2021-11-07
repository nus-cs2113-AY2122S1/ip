package task;

/**
 * Represents class for todo tasks.
 */
public class ToDo extends Task {
    public static final String type = "T";

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides the toString method to print the todo task.
     *
     * @return [T] + statusIcon + description
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    /**
     * Returns the type of todo tasks.
     *
     * @return Task type "T"
     */
    @Override
    public String getTaskType() {
        return type;
    }
}