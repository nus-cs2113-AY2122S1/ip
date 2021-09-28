package karlett.task;

public class Event extends Task {

    public String getAt() {
        return at;
    }

    protected String at;

    /**
     * Return an event object, setting its task
     * status to false by default. This constructor
     * is used for user input.
     *
     * @param description details of an event
     * @param at time of the event
     */
    public Event(String description, String at) {
        this.description = description;
        this.isDone = false;
        this.at = at;
    }

    /**
     * Return an Event object, setting its task status
     * according to the task status given. This constructor
     * is used for loading file data.
     *
     * @param description details of an event
     * @param at time of the event
     * @param isDone task status of the event
     */
    public Event(String description, String at, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + at + ")";
    }
}
