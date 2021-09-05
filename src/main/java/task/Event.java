package task;

public class Event extends Task {
    protected String atWhen;

    public Event (String taskName, String atWhen) {
        super(taskName);
        this.atWhen = atWhen;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At -> " + atWhen + ")";
    }
}
