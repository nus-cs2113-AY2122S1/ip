public class Event extends Task{
    protected String eventDate;

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    public String toString() {
        return ("[E][" + getStatusIcon() + "] " + description + " (at: " + eventDate + ")");
    }
}
