package alfred.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This method retrieves the date specified for Deadline.
     * @return String Date string to be returned
     */
    public String getDate() {
        return by;
    }

    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
