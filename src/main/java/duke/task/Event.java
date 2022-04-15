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
     * @param description The description of the Event.
     * @param at          The timing information of the Event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        super.setDateAndTime(at);
    }

    /**
     * Gets the timing information of the Event.
     *
     * @return The timing information of the Event.
     */
    public String getAt() {
        return at;
    }

    /**
     * Sets the date and time of the Event task.
     *
     * @param line String containing timing information.
     */
    @Override
    public void setDateAndTime(String line) {
        super.setDateAndTime(line);
    }

    /**
     * Provides a String representation of Event. If this Event task has
     * a valid date and time, the timing information will be displayed
     * in a different format (MMM dd yyyy HH:mm).
     *
     * @return A String representation of the Event including
     * its timing information.
     */
    @Override
    public String toString() {
        if (hasDateTime) {
            at = getDateAndTime();
        }
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
