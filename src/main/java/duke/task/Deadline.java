package duke.task;

import static duke.Duke.DATA_FILE_SEPARATOR;

/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    public static final String TASK_TYPE_ICON = "D";
    /** Deadline for the task (date/time) */
    protected String by;

    /**
     * Creates a task with the specified description and deadline.
     *
     * @param description Description of the task.
     * @param by          Deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
    }

    @Override
    public String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }

    /**
     * Returns a string representation of the task (consisting of the deadline, as well as the string representation
     * from the parent class).
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataString() {
        return super.toDataString() + DATA_FILE_SEPARATOR + by;
    }
}
