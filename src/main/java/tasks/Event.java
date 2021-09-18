package tasks;
public class Event extends Task {
    protected String at;
    public static final String EVENT_AT = "(at:";

    public Event(String name, String at) {
        this(name, at, false);
    }

    public Event(String name, String at, boolean isDone) {
        super(name, isDone);
        this.taskType = TaskType.EVENT;
        this.at = at;
        this.taskChar = 'E';
        this.fullDescription = name + " " + EVENT_AT + " " + this.at + ")";
    }

    public String getDate() {
        return at;
    }
}
