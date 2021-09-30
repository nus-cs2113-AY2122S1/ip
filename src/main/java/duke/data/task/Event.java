package duke.data.task;

/**
 * An event task in the task list.
 */
public class Event extends Task{
    protected String at;

    /**
     * Constructor that sets the description and at.
     *
     * @param description a string that contains the task description
     * @param at a string that contains the date/time for the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a String of value at, representing the date/time of the event
     *
     * @return the date/time of the task
     */
    public String getAt() {
        return at;
    }

    /**
     * Returns the String representation of the Event task
     *
     * @return a String message that contains the status icon, task description and event date/time
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
