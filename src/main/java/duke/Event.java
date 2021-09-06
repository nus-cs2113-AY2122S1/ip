package duke;

public class Event extends Task {
    private static final String EVENT_ICON = "[E]";

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return EVENT_ICON + super.toString()
                + " (at: " + at + ")";
    }
}
