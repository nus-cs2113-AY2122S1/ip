package austin;

public class Event extends Task {
    /** Event date and time*/
    protected String at;

    public Event(String description, String at) {
        super(description);
        setAt(at);
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " # " + description + " @ " + at + "\n";
    }

    @Override
    public String toString() {
        return"[D] [" + getStatus() + "] " + getDescription() + " (at:" + at + ")";
    }
}