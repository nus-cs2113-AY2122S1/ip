package duke.task;

/**
 * Event task that has a date and duration
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the date, start and end time of event
     * @return date and time of event
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Returns the char that represents event tasks
     * @return char for identifying events
     */
    public String getCode() {
        return "E";
    }

    /**
     * Set the string, at
     * @param at the start and end time of event
     */
    public void setAt(String at) {
        this.at = at;
    }

    /**
     * To print event task in a certain format
     * @return String that shows the information and status of event task
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.getDescription() +
                " (at: " + this.getAt() + ")";
    }
}
