public class Event extends Task {
    protected String when;

    public Event(String description, String when) {
        super(description);
        setWhen(when);
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + when + ")";
    }
}
