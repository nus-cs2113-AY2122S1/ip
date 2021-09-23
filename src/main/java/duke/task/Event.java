package duke.task;

/**
 * Represents tasks which are events
 */
public class Event extends Task{
    
    protected String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    /**
     * sets the timing of the event
     *
     * @param at the timing of the event
     */
    public void setAt(String at) {
        this.at = at;
    }

    /**
     * returns the timing of the event
     *
     * @return the timing of the event
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Overrides default toString method with the custom print message
     *
     * @return the custom print message
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
