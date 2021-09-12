package task;

public class Event extends Task {
    protected String atWhen;

    public Event(String taskName, String atWhen) {
        super(taskName);
        this.atWhen = atWhen;
    }

    public Event(String taskName, String atWhen, boolean isDone) {
        super(taskName, isDone);
        this.atWhen = atWhen;
    }

    public String getAtWhen() {
        return atWhen;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At -> " + atWhen + ")";
    }
}
