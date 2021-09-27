package tasklist;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    /**
     * This method overrides the toString method in Task
     * @return the type icon followed by the usual toString method of Task and
     * to print the event date & time at the end of the description
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * This method overrides the getDescription method in Task
     * @return adds a comma between 'description' and 'at' strings to follow
     * the CSV format when saving to text file
     */
    @Override
    public String getDescription() {
        return description + "," + at;
    }
}
