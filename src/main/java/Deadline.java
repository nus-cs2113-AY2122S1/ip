public class Deadline extends Task{
    /** Deadline date and time */
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + (getBy()).substring(3) + ")";
    }
}
