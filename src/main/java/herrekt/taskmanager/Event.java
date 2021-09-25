package herrekt.taskmanager;

public class Event extends Task {
    private final String date;

    /**
     * Initialise an Event with a description and date.
     *
     * @param description Description of the event.
     * @param date Further details of when it occurs.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    protected String getDate() {
        return date;
    }

    /**
     * Returns the save format of the current Event.
     * Converts the event into a string format recognizable in the save file.
     *
     * @return The event as a string.
     */
    @Override
    protected String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "E" + SAVE_FILE_SPACER
                + done + SAVE_FILE_SPACER
                + this.description + SAVE_FILE_SPACER
                + this.date;
    }

    protected String getDescription() {
        return super.description + " (at: " + this.getDate() + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDate() + ")";
    }
}
