package task;

/**
 * Represents class for event tasks
 */
public class Event extends Task {
    protected String at;
    public static final String type = "E";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public Event( String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the time at which the event task will take place.
     *
     * @return Time of event task as a String
     */
    public String getAt(){
        return at;
    }

    /**
     * Overrides the toString method to print the event task.
     *
     * @return [E] + statusIcon + description + (at: " ")
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the type of event tasks.
     *
     * @return Task type "E"
     */
    @Override
    public String getTaskType() {
        return type;
    }
}