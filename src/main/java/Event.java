public class Event extends Task {
    protected String description;
    protected String event;

    public Event(String description, String event) {
        super(description);
        this.event = event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + event + ")";
    }
}