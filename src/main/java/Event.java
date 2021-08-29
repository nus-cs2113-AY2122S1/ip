public class Event extends Task {

    private static final String SYMBOL = "E";
    private String eventDateTime;

    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + eventDateTime + ")";
    }
}
