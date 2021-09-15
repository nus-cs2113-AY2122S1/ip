public class Event extends Task {
    protected String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at.trim();
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + "|" + at;
    }
}
