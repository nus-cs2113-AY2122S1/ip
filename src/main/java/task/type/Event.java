package task.type;

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
     * Returns time of event
     *
     * @return time of event
     */
    public String getAt(){
        return at;
    }

    /**
     * Prints the event task in the display format
     *
     * @return event task in the correct format to be printed.
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns type of task
     *
     * @return 'E' which is the task type for event
     */
    @Override
    public String getTaskType() {
        return type;
    }
}