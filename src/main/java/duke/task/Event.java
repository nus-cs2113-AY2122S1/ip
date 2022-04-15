package duke.task;

/**
 * Event object contains description of the event and date and time of the event
 */
public class Event extends Task {
    protected String description;
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }

    @Override
    public String toSave() {
        return "E |" + super.toSave() + "|" + at;
    }
}