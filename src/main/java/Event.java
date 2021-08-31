public class Event extends Task {

    protected String at;

    public Event(String description) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[Event]" + getStatusIcon() + super.toString() + " (at: " + at + ")";
    }
}
