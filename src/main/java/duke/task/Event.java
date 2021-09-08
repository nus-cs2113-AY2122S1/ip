package duke.task;

public class Event extends Task {

    protected String on;

    /**
     * Constructor to initialise the description
     * and the date/time of the event.
     *
     * @param description string with the event
     *                    description
     * @param on          date/time for the event
     */
    public Event(String description, String on) {
        super(description);
        this.on = on;
    }

    /**
     * Returns the formatted Description of the event.
     *
     * @return returns a String with the event description
     */
    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (on: " + on + ")";
    }
}
