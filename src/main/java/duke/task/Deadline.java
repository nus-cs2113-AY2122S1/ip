package duke.task;

public class Deadline extends Task {
    protected String timing;

    public Deadline(String description, String by) {
        super(description);
        this.timing = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        this(description, by);
        super.taskDone(isDone);
    }

    public String getIcon() {
        return "D";
    }

    public String getTiming() {
        return "(by:" + timing + ")";
    }

    public String getTime() {
        return timing;
    }

    @Override
    public String toString() {
        return "[" + getIcon() + "]" + super.toString() + getTiming();
    }
}