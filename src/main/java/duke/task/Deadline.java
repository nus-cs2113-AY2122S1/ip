package duke.task;

public class Deadline extends Task {
    /** Task deadline. */
    protected String dateOrTime;

    /**
     * Initializes the task description and deadline, and
     * sets initial status to "not done".
     *
     * @param description Task description.
     * @param dateOrTime Task deadline.
     */
    public Deadline(String description, String dateOrTime) {
        super(description);
        this.dateOrTime = dateOrTime;
    }

    /**
     * Initializes the task description and deadline, and
     * sets initial status according to the given parameter.
     *
     * @param description Task description.
     * @param dateOrTime Task deadline.
     * @param isDone Initial status.
     */
    public Deadline(String description, String dateOrTime, boolean isDone) {
        super(description, isDone);
        this.dateOrTime = dateOrTime;
    }

    @Override
    public String serialize() {
        return DEADLINE + " | " + (isDone ? "1" : "0") + " | " + description + " | " + dateOrTime;
    }

    @Override
    public String toString() {
        return "[" + DEADLINE + "]" + super.toString() + " (by: " + dateOrTime + ")";
    }
}
