package duke.task;

public class Event extends Task {
    protected String timing;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    public String getIcon() {
        return "[E]";
    }

    public String getTiming() {
        return "(at:" + timing + ")";
    }

    @Override
    public String toString() {
        return getIcon() + super.toString() + getTiming();
    }
}
