package duke;

/**
 * Implements the class Event, which has a description, a done status
 * and a detail stating when or where it is at.
 */
public class Event extends Task {

    private final String taskType = "E";
    /** The time or venue of the event. */
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        super.setDone(isDone);
    }

    @Override
    public boolean contains(String search) {
        boolean descriptionContains = super.contains(search);
        boolean atContains = at.toLowerCase().contains(search);
        return descriptionContains || atContains;
    }

    @Override
    public String taskString() {
        return this.taskType + " | " + super.getStatusIcon() + " | " + super.description + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
