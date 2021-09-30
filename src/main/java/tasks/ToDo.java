package tasks;

/**
 * Represents a ToDo Task in the task list.
 */
public class ToDo extends Task {

    public static final String INITIAL = "T";

    public ToDo(String description) {
        super(description);
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}