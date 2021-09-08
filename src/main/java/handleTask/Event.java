package handleTask;

public class Event extends Task {
    private String eventDate;

    public Event(String description, String eventDate) {
        super(description, 'E');
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "]"
                + description + " (at:" + eventDate + ")";
    }
}
