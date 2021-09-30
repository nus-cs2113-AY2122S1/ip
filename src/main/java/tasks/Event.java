package tasks;

/**
 * Represents the interactions with the event task.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Sets the time range of this event task.
     *
     * @param at Time range to be set.
     */
    public void setDeadline(String at) {
        this.at = at;
    }

    /**
     * Returns the time range of this task.
     *
     * @return Time range of the task.
     */
    public String getTimeRange() {
        return at;
    }

    /**
     * Returns the task information in String format.
     *
     * @return Task information.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
