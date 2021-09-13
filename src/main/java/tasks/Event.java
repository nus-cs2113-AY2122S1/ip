package tasks;

public class Event extends Task {
    private String eventDate;
    public Event(boolean done, String name, String date) {
        super(done, name);
        this.eventDate = date;
    }

    public Event() {
        super(false, "Nothing");
        this.eventDate = "never";
    }

    public String getEventDate() {
        return eventDate;
    }

    @Override
    public String getPrefix() {
        return "[E]";
    }

    @Override
    public String toString() {
        return getPrefix() + super.toString() + "(at: " + eventDate + ")";
    }
}
