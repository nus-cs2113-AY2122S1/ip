package duke.task;

public class Event extends Task {
    /** Time period of the task. */
    protected String at;

    /**
     * Initializes the task description and deadline, and
     * sets initial status to "not done".
     *
     * @param description task description
     * @param at time period of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Initializes the task description and deadline, and
     * sets initial status according to the given parameter.
     *
     * @param description task description
     * @param at time period of the task
     * @param isDone initial status
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String serialize() {
        return "E" + " | " + (isDone ? "1" : "0") + " | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return "[" + EVENT + "]" + super.toString() + " (at: " + at + ")";
    }
}
