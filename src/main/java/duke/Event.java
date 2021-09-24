package duke;

/**
 * Represents an event and time.
 */
public class Event extends Task {
    private static final String EVENT_ICON = "[E]";

    private String at;

    /**
     * Instantiates a <code>Event</code> <code>Task</code>.
     *
     * @param description Description of the event.
     * @param at the time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the time the <code>Event</code> occurs.
     *
     * @return Time of occurrence of the event
     */
    public String getAt() {
        return at;
    }

    /**
     * Formats the <code>Event</code> to <code>String</code>.
     *
     * @return Formatted event to user-friendly output.
     */
    @Override
    public String toString() {
        return EVENT_ICON + super.toString()
                + " (at: " + at + ")";
    }
}
