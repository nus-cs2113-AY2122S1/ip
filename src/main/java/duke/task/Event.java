package duke.task;

/**
 * Inherits from Task. Represents an event and
 * tracks timing information.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructs an Event with a description and
     * timing information.
     *
     * @param description The description of the event.
     * @param at          The timing information of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the timing information of the event.
     *
     * @return The timing information of the event.
     */
    public String getAt() {
        return at;
    }

    /**
     * Provides a String representation of Event.
     *
     * @return A String representation of the Event including
     * its timing information.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
