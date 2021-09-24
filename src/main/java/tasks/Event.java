package tasks;

public class Event extends Task {
    protected String from;

    public Event(String description, String from) {
        super(description);
        this.from = from;
    }

    public void setDeadline(String from) {
        this.from = from;
    }

    public String getTimeRange() {
        return from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + from + ")";
    }
}
