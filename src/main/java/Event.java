public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Get the string, at, that determines the start and end time of event
     * @return String at
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Set the string, at
     * @param at the start and end time of event
     */
    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.getDescription() +
                " (at: " + this.getAt() + ")";
    }
}
