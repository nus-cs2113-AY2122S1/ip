package duke.task;

public class Event extends Task {
    /** Time period of the task. */
    protected String dateOrTime;

    /**
     * Initializes the task description and deadline, and
     * sets initial status to "not done".
     *
     * @param description Task description.
     * @param dateOrTime Time period of the task.
     */
    public Event(String description, String dateOrTime) {
        super(description);
        this.dateOrTime = dateOrTime;
    }

    /**
     * Initializes the task description and deadline, and
     * sets initial status according to the given parameter.
     *
     * @param description Task description.
     * @param dateOrTime Time period of the task.
     * @param isDone Initial status.
     */
    public Event(String description, String dateOrTime, boolean isDone) {
        super(description, isDone);
        this.dateOrTime = dateOrTime;
    }

    @Override
    public String serialize() {
        return EVENT + " | " + (isDone ? "1" : "0") + " | " + description + " | " + dateOrTime;
    }

    @Override
    public String toString() {
        return "[" + EVENT + "]" + super.toString() + " (at: " + dateOrTime + ")";
    }
}
