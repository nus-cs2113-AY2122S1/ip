package duke.task;

public class Event extends Task {
    protected String timing;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    public Event(String description, String timing, boolean isDone) {
        this(description, timing);
        super.taskDone(isDone);
    }

    public String getIcon() {
        return "E";
    }

    public String getTiming() {
        return "(at:" + timing + ")";
    }

    public String getTime() {
        return timing;
    }

    @Override
    public String toString() {
        return "[" + getIcon() + "]" + super.toString() + getTiming();
    }
}
