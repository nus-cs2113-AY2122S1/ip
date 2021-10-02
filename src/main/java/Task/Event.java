package Task;

/**
 * inherit Task class as an Event task
 * contains description of Event task and String event
 */
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