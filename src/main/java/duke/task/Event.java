package duke.task;

public class Event extends Task {

    private static final String SYMBOL = "E";
    private static final String SEPARATOR = " | ";
    private String eventDateTime;

    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + eventDateTime + ")";
    }

    @Override
    public String toData() {
        return SYMBOL + SEPARATOR + (isDone ? 1 : 0) + SEPARATOR + description + SEPARATOR + eventDateTime;
    }
}
