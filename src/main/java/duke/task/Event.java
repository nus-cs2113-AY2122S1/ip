package duke.task;

/**
 * The Event task is a subclass of the Task class.
 * It is a specific class that also has a duration (<code>duration</code>).
 */
public class Event extends Task{
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String listTask() {
        return "[E]" + super.listTask() + " (at: " + duration + ")";
    }

    @Override
    public String getIcon() {
        return "E";
    }

    public String getDate() {
        return duration;
    }
}
