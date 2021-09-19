package duke.tasks;

/**
 * Represents a <code>Task</code> that has a specific date and time of occurrence.
 */
public class Event extends Task {

    private static final String SYMBOL = "E";
    private static final String SEPARATOR = " | ";
    private String eventDateTime;

    /**
     * Constructs an <code>Event</code> object with all its information such as its description and date and time.
     *
     * @param description <code>String</code> description of the task
     * @param eventDateTime <code>String</code> date and time of the task
     */
    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + eventDateTime + ")";
    }

    @Override
    public String toDataStringFormat() {
        return SYMBOL + SEPARATOR + (isDone ? 1 : 0) + SEPARATOR + description + SEPARATOR + eventDateTime + "\n";
    }
}
