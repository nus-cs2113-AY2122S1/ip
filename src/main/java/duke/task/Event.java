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

    /**
     * Formats description of task to be saved as txt
     *
     * @return Formatted string of a task
     */
    @Override
    public String toSave() {
        return String.format("%s,%d,%s,%s", this.getType(), this.getIsDone(), this.getDescription(),
                this.getDuring());
    }
}