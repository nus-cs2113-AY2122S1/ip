package duke.data.task;

public class Deadline extends Task {

    /* Task type indicator */
    public static final char TASK_TYPE = 'D';

    private final String deadline;

    /**
     * Constructor for Task of type Event
     *
     * @param description Description of the task to add.
     * @param deadline    Deadline of task
     */
    public Deadline(String description, String deadline) {
        super(description, TASK_TYPE);
        this.deadline = deadline;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return deadline of the task.
     */
    @Override
    public String getDeadline() {
        return deadline;
    }

    /**
     * Formats details of the Task to a printable string.
     *
     * @return Formatted string of a task.
     */
    @Override
    public String toFormattedString() {
        return String.format("%s (at: %s)", super.toFormattedString(), this.deadline);
    }
}
