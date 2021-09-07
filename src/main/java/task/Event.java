package task;

public class Event extends Task {
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        String[] newBy = by.split(" ", 2);
        return newBy[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getBy() + ")";
    }
}
