package duke.task;

public class Event extends Task{

    protected String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
        this.taskType = "E";
    }

    /**
     * Returns the description of the event task, followed by "/at" and its stored due date.
     *
     * @return Description of task, "/at", and due date of task.
     */
    @Override
    public String getDescription() {
        return this.description + "/at" + this.eventTime;
    }

    /**
     * Returns the icon, status, description and due date of the event task with the appropriate formatting.
     *
     * @return Icon, status, description and due date of task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + eventTime + ")";
    }
}
