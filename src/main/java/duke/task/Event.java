package duke.task;

/**
 * Class that encapsulates an Event task
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for an Event object
     *
     * @param description The name of the Event task
     * @param at Time that the event takes place
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * To String method that returns a string representation of the Event task for user output
     *
     * @return A string representation of the Event task formatted for user output,
     * consisting of its description, status and time
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Method that returns a string representation of the Event task to write to file
     *
     * @return A string representation of the Event task formatted for file writing,
     * consisting of its description, status and time
     */
    @Override
    public String parseDataIntoString() {
        return "E" + super.parseDataIntoString() + " | " + this.at;
    }
}
