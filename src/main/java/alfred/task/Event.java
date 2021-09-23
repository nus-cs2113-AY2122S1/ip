package alfred.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * This method retrieves the date specified for Event.
     * @return String Date string to be returned
     */
    public String getDate() {
        return at;
    }

    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
