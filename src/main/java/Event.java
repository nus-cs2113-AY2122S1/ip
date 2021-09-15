public class Event extends Task {
    private String eventDate;

    public Event(String description, String eventDate) {
        super(description, 'E');
        this.eventDate = eventDate;
    }
    public Event(String description,boolean isDone, String eventDate) {
        super(description, 'E',isDone);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "] "
                + description + " (at: " + eventDate + ")";
    }

    public String getDate() {
        return eventDate;
    }
}
