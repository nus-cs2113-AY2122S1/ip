package duke.task;

public class Event extends Task {
    protected String at;
    protected String type;

    public Event(boolean isDone, String TaskName, String at) {
        super(TaskName);
        this.isDone = isDone;
        this.at = at;
        this.type = "E";
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toSaveFile(String DELIMITER) {
        return "E" + super.toSaveFile(DELIMITER) + DELIMITER + at;
    }
}
