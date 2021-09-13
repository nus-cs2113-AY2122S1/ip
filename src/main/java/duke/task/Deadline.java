package duke.task;

public class Deadline extends Task {
    private String dateTime;

    public Deadline(String description, TaskType type, String dateTime) {
        super(description, type);
        this.dateTime = dateTime;
    }

    public Deadline(String description, TaskType type, boolean isDone, String dateTime) {
        super(description, type);
        this.isDone = isDone;
        this.dateTime = dateTime;
    }

    @Override
    public String getFileStringFormat() {
        return String.format("D | %s | %s", super.getFileStringFormat(), this.dateTime);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("(by: %s)", dateTime);
    }
}
