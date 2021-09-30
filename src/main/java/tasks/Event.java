package tasks;

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

    public String toString() {
        return ("[E][" + getStatusIcon() + "] " + description + " (at:" + eventTimeRange + ")");
    }
}
