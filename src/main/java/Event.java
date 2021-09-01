/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    /** Date and time of the event */
    protected String at;

    /**
     * Creates a task with the specified description and event date/time.
     *
     * @param description Description of the event.
     * @param at Date and time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of the event (consisting of the symbol for the task type, the event date and
     * time, as well as the string representation from the parent class).
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
