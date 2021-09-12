package duke.task;

public class Event extends Task {
    protected final String TASK_TYPE = "E";
    protected String when;

    public Event(String description, String when) {
        super(description);
        setWhen(when);
    }

    public String getTaskType() {
        return TASK_TYPE;
    }


    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (at: " + when + ")";
    }
}
