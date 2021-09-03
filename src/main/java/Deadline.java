public class Deadline extends Task {

    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String DEADLINE_ICON = "D";

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + DEADLINE_ICON + CLOSE_BRACKET + super.toString() + " (by: " + by + ")";
    }
}
