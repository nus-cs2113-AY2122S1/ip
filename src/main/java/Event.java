public class Event extends Task {
    protected String eventTimeRange;

    public Event(String description, String eventTimeRange) {
        super(description);
        this.eventTimeRange = eventTimeRange;
    }

    public String toString() {
        return ("[E][" + getStatusIcon() + "] " + description + " (at:" + eventTimeRange + ")");
    }
}
