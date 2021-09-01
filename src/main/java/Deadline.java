public class Deadline extends Task {

    protected String deadline;

    /**
     * Constructor for Task of type Deadline
     *
     * @param description Description of the task to add.
     * @param deadline    Deadline of task
     */
    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return deadline of the task.
     */
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
        return String.format("%s (by: %s)", super.toFormattedString(), this.deadline);
    }
}
