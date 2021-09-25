package duke.task;

public class Event extends Task{
    protected String at;
    private static final String ICON_EVENT = "E";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskIcon() {
        return ICON_EVENT;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString() + " at: " + at;
    }

    @Override
    public String getDue() {
        return at;
    }
}
