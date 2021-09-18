package duke.task;

/**
 * Represents a Task item containing description and the time when it occurs
 */
public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        this(description, at, false);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Get the string describing the status of the event task
     * 
     * @return String formatted by event, completion status, description, at
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), this.description, this.at);
    }

    /**
     * Get the string format of event task to be saved
     * 
     * @return String formatted by event, description, completion status, at
     */
    @Override
    public String toSave() {
        return String.format("event | %s | %b | %s", this.description, this.isDone, this.at);
    }
}
