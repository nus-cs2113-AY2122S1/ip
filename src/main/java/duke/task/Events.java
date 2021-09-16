package duke.task;

public class Events extends Task {
    protected String time;

    public Events(String description, String time) {
        super(description);
        this.time = time;
    }
    @Override
    public String getDate() {
        return this.time;
    }
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
