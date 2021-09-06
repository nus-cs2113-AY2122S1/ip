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
     * The constructor method. Initializes the task
     * description, status, and time period.
     *
     * @param description Task description.
     * @param at Time period of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of Event.
     *
     * @return The string representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
