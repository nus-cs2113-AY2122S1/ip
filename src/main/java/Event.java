public class Event extends Task {
    private String atTime;

    public static final String PREPOSITION = "at";

    public Event(String description, String atTime) {
        super(description);
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s: %s)", super.toString(), PREPOSITION, atTime);
    }
}
