package duke.task;

public class Event extends Task {

    /* Date and time of event */
    protected String during;

    /**
     * Constructor for event type task
     *
     * @param description Description of the event task processed by TaskManager
     * @param during      Date and time of event processed by TaskManager
     */
    public Event(String description, String during) {
        super(description, 'E');
        this.during = during;
    }

    /* Getter for date and time of event */
    public String getDuring() {
        return during;
    }

    /**
     * Formats description of event task type to be displayed to user
     *
     * @return Formatted string of an event task type
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.getDuring());
    }
}