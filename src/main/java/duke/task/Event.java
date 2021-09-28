package duke.task;

public class Event extends Task{
    protected String at;
    protected String icon;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.icon = "E";
    }

    public String getAt() {
        return at;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "[" + getIcon() + "]" + super.toString() + " (at: " + getAt() + ")";
    }

    public String formatForDataStore() {
        return "E|" + ((getIsDone()) ? 1 : 0) + "|" + getDescription() + "|" + getAt() + "\n";
    }
}