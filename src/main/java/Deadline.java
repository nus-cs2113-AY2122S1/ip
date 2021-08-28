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
        return "[" + icon + "]" + super.toString() + " (by: " + by + ")";
    }
}