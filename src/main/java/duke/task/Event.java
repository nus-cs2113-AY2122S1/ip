package duke.task;

public class Event extends Task {
    private String atDate;

    public Event(String taskName, String atDate) {
        super(taskName);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(at: " + atDate + ")";
    }

    @Override
    public String getStorageString() {
        String c = isCompleted ? "1" : "0";
        return "E | " + c + " | " + taskName + " | " + atDate;
    }
}
