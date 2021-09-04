package duke.task;

public class Event extends Task{
    protected String at;
    protected String icon;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.icon = "E";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (at: " + at + ")";
    }
}