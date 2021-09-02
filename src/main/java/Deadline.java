public class Deadline extends Task {

    protected String by;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return ("[D][" + getStatusIcon() + "] " + description + " (by: " + by + ")");
    }
}