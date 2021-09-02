public class Event extends Task {

    public Event(String description, String by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (at: " + by + ")";
    }
}
