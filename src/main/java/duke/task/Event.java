package duke.task;

public class Event extends Task implements TaskHelper {

    protected String deadline;

    /**
     * Constructor for Task of type Event
     *
     * @param description Description of the task to add.
     * @param deadline    Deadline of task
     */
    public Event(String description, String deadline) {
        super(description, TaskType.EVENT);
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
        return String.format("%s (at: %s)", super.toFormattedString(), this.deadline);
    }
}
