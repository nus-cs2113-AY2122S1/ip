package task.type;

public class Event extends Task {
    protected String at;
    public static final String type = "E";
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event( String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt(){
        return at;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getTaskType() {
        return type;
    }
}