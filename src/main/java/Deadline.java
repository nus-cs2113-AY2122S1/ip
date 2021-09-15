public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by.trim();
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "|" + by;
    }
}