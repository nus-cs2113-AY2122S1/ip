/**
 * This class deals with operations on Event tasks.
 */
public class Event extends Task {

    protected String date;

    /**
     * Constructor of Event class.
     *
     * @param description description of the Event task
     * @param date date of the Event task
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the type indicator of the Event task.
     *
     * @return type indicator of the Event
     */
    public String getType() {
        return "E";
    }

    /**
     * Returns the date of the Event task.
     *
     * @return date of the Event
     */
    public String getDate() {
        return date;
    }

    @Override
    /**
     * Returns the done status, type, description, and date of the Event task in String format.
     *
     * @return type, description, date of Event
     */
    public String toString() {
        return " [E]" + super.toString() + " (at: " + date + ")";
    }
}
