package duke.task;

public class Event extends Task{

    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
        this.taskType = "E";
    }

    @Override
    public String getDescription() {
        return this.description + "/at" + this.eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + eventTime + ")";
    }
}
