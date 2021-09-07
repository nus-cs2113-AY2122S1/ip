package duke.task;

/**
 * This class is used for tasks that start at a specific time and ends at a specific time.
 * E.g: team meeting at 9pm
 */
public class Event extends Task {
    protected String dateAndTime;
    protected static final String EVENT_LOGO = "[E]";

    public Event(String description, String dateAndTime) {
        super(description);
        setDateAndTime(dateAndTime);
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    /**
     * Returns Event task formatted in the form "[E][ ] description (at: date/time)"
     *
     * @return Formatted Event task string
     */
    @Override
    public String toString() {
        return EVENT_LOGO + super.toString() + " (at: " + dateAndTime + ")";
    }
}
