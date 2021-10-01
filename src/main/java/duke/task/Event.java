package duke.task;

/**
 * Represents an event that has a description, a status inherited from Task class,
 * and an extra at attribute indicating the time of the task.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(int status, String description, String at) {
        super(status, description);
        this.at = at;
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String getStatusNumber() {
        return super.getStatusNumber();
    }

    public String getTime() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
