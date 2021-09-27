package duke.task;

public class Event extends Task {

    protected String at;
    protected String type = "E";

    /**
     * Constructor to create Event object.
     *
     * @param description Event description.
     * @param at Event timings.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Retrieve timings of events.
     *
     * @return timings of the Event.
     */
    public String getTime() {
        return this.at;
    }

    /**
     * Retrieve the task type.
     *
     * @return Task type: "E".
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}