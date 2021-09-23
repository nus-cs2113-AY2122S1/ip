package task;

public class Event extends Task {
    /** Event date and time */
    protected String at;

    public Event(String description, String at) {
        super(description);
        setAt(at);
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toFileFormat() {
        return "E # " + (isDone ? "1" : "0") + " # " + description + " # " + at + "\n";
    }

    @Override
    public String toString() {
        return "[E] [" + getStatus() + "] " + getDescription() + " (at: " + at + ")";
    }
}