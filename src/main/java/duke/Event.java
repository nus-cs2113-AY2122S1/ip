package duke;

public class Event extends Task {
    private String atDate;

    public Event(String taskName, String atDate) {
        super(taskName);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(by: " + atDate + ")";
    }
}
