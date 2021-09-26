package duke.task;

/**
 * Represents an Event Task.
 */
public class Event extends Task{

    private static final String ICON_EVENT = "E";
    protected String at;

    /**
     * Create an Event object.
     *
     * @param description The description of the Event task.
     * @param at The time of the Event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskIcon() {
        return ICON_EVENT;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() + "]" + super.toString() + " at: " + at;
    }

    @Override
    public String getDue() {
        return at;
    }
}
