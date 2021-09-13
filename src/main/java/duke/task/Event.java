package duke.task;

public class Event extends Task {
    private String dateTime;

    public Event(String description, TaskType type, String dateTime) {
        super(description, type);
        this.type = TaskType.EVENT;
        this.dateTime = dateTime;
    }

    public Event(String description, TaskType type, boolean isDone, String dateTime) {
        super(description, type);
        this.type = TaskType.EVENT;
        this.isDone = isDone;
        this.dateTime = dateTime;
    }

    @Override
    public String getFileStringFormat() {
        return String.format("E | %s | %s", super.getFileStringFormat(), this.dateTime);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("(at: %s)", dateTime);
    }
}
