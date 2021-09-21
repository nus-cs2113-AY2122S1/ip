package duke.task;

/**
 * A Task that contains a description field.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task instance.
     *
     * @param description The description of a Task given by user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns Status of task in String format.
     *
     * @return Status of task in String format.
     */
    public String getStatus() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatus() + " " + getDescription();
    }
}
