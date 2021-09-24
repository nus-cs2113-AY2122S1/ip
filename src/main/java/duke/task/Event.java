package duke.task;

public class Event extends Task {
    private String at;

    /**
     * Constructor to create an Event task. Constructor takes in the description and time in which the event will occur.
     * @param description Description of the event type task.
     * @param at Date when the event will occur.
     */
    public Event(String description, String at) {
        super(description, "E");
        setAt(at);
    }

    /**
     * Sets the date where the event task will occur.
     * @param at Date when the event will occur.
     */
    public void setAt(String at) {
        this.at = at;
    }

    /**
     * Retrieves the date where the event will occur
     * @return String containing the date where the event will occur.
     */
    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
