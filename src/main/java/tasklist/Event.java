package tasklist;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    /**
     * Returns additional information which is the type icon and event date & time
     * at the end when toString method is called.
     * @return the type icon followed by the usual toString method of Task and
     * the event date & time at the end
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Adds additional character to support file format when saving event
     * @return adds a comma between 'description' and 'at' strings to follow
     * the CSV format when saving to text file
     */
    @Override
    public String getDescription() {
        return description + "," + at;
    }
}
