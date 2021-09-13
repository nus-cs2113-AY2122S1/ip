package duke.task;

public class Deadline extends Task {
    protected String by;
    protected String icon;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.icon = "D";
    }

    @Override
    public String toString() {
        return "[" + getIcon() + "]" + super.toString() + " (by: " + getBy() + ")";
    }

    public String getBy() {
        return by;
    }

    public String getIcon() {
        return icon;
    }

    public String formatForDataStore() {
        return "D|" + ((getIsDone()) ? 1 : 0) + "|" + getDescription() + "|" + getBy() + "\n";
    }
}