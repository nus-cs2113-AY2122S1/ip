package duke.task;

/**
 * Subclass of {@code Task} which represents a daily todo with no extra attributes.
 */
public class Todo extends Task {
    protected final String TASK_TYPE = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }
}
