package tasks;

/**
 * Represents an Event Task in the task list.
 */
public class Event extends Task {

    public static final String INITIAL = "E";

    protected String eventTimeRange;

    public String getEventTimeRange() {
        return eventTimeRange;
    }

    public Event(String description, String eventTimeRange) {
        super(description);
        this.eventTimeRange = eventTimeRange;
    }

    @Override
    public String toString() {
        return ("[E][" + getStatusIcon() + "] " + description + " (at:" + eventTimeRange + ")");
    }
}
