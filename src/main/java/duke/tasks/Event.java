package duke.tasks;

/**
 * Event is a Sub-class that inherits from Task Class
 * An Event object is represented by a description of the task and when the Event is due at.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toFileFormat() {
        return "E|" + super.toFileFormat() + "|" + at;
    }
}
