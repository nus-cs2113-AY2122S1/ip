package task;

public class Deadline extends Task {
    /** Deadline date and time */
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D # " + (isDone? "1" : "0") + " # " + description + " # " + by + "\n";
    }

    @Override
    public String toString() {
        return "[D] [" + getStatus() + "] " + getDescription() + " (by: " + by + ")";
    }
}