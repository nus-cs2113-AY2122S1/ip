public class Deadline extends Task {
    protected String by;
    protected String taskName;
    protected int index;

    public Deadline(String description, String by, int index) {
        super(description);
        this.by = by;
        this.index = index;
    }

    public String getBy() {
        return this.by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String toString() {
        taskName = description.substring(9, index - 2);
        return "[D][ ]" + taskName + " (by: " + by + ")";
    }
}