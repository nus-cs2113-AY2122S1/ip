package duke.data.task;

/**
 * Represents tasks that start at a specific time and ends at a specific time.
 * E.g: team meeting at 9pm
 */
public class Event extends Task {
    protected String dateAndTime;

    public Event(String description, String dateAndTime) {
        super(description);
        setDateAndTime(dateAndTime);
    }

    public Event(String description, String dateAndTime, boolean isDone) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    /**
     * Returns Event task formatted for application UI,  in the form "[E][ ] description (at: date/time)"
     *
     * @return Formatted Event task string
     */
    @Override
    public String toString() {
        return EVENT_LOGO + super.toString() + " (at: " + dateAndTime + ")";
    }

    /**
     * Returns Event task formatted for data file in the form "E | 1/0 | description | dateAndTime"
     *
     * @return Formatted Event task string for data file
     */
    @Override
    public String toTextFileString() {
        return EVENT_ACRONYM + " | " + super.toTextFileString() + " | " + dateAndTime;
    }
}
