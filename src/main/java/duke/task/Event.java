package duke.task;

public class Event extends Task {
    private String eventTime;

    public Event(String description) {
        super(description.substring(6, description.indexOf(" /at")));
        this.eventTime = description.substring(description.indexOf("/at") + 4);
    }

    public String getEventTime() {
        return eventTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }
}
