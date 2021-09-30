public class Event extends Task {
    protected String at;
    protected String taskName;
    protected int index;

    public Event(String description, String at, int index) {
        super(description);
        this.at = at;
        this.index = index;
    }

    public String getAt() {
        return this.at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String toString() {
        taskName = description.substring(6, index - 1);
        return "[E][ ] " + taskName + " (at: " + at.substring(3) + ")";
    }
}