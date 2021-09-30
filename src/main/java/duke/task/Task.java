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
     * Returns status of Task in String format.
     * Tasks that are done will return "[X]" while those that are not will return "[ ]".
     * 
     * @return Status of Task in String format.
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
