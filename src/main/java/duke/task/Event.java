package duke.task;

/**
 * The Event class is a subclass of Task. It
 * provides an additional parameter, time period.
 *
 * @author richwill28
 */
public class Event extends Task {
    /** Time period of the task */
    protected String at;

    /**
     * The constructor method. Initialize task description,
     * status to "not done", and time period.
     *
     * @param description Task description.
     * @param at Time period of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * The constructor method. Initialize task description,
     * status according to the given parameter, and deadline.
     *
     * @param description Task description.
     * @param at Time period of the task.
     * @param isDone Initial status.
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
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
