public class Event extends Task {

    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String EVENT_ICON = "E";

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + EVENT_ICON + CLOSE_BRACKET + super.toString() + " (at: " + at + ")";
    }
}
