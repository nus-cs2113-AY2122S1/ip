package tasklist;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * This method overrides the toString method in Task
     * @return the type icon followed by the usual toString method of Task and
     * to print the deadline date & time at the end of the description
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * This method overrides the getDescription method in Task
     * @return adds a comma between 'description' and 'by' strings to follow
     * the CSV format when saving to text file
     */
    @Override
    public String getDescription() {
        return description + "," + by;
    }
}
