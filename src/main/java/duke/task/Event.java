package duke.task;

/**
 * Shows the instances and methods of a event task.
 */
public class Event extends Task{

    protected String eventTime;

    public Event(String eventName, String eventTime) {
        super(eventName);
        this.eventTime = eventTime;
        this.taskType = "E";
    }

    @Override
    public String getTaskDescription() {
        return this.taskName + "/at" + this.eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + eventTime + ")";
    }
}