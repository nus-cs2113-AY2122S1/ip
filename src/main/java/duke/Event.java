package duke;

/**
 * Class that is used in order to create a new event task.
 *
 * @author pragyan01
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor to instantiate new event object
     *
     * @param description Description of the event task
     * @param at Due date of the event task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Method to return String that has been stored in the Arraylist.
     *
     * @return String stored in the ArrayList
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.toString() + "(at: " + at + ")";
    }

    /**
     * Method to return String that has been stored in the Arraylist.
     *
     * @return String stored in the ArrayList
     */
    @Override
    public String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "event " + description + "/at " + at + " | " + done;
    }
}
