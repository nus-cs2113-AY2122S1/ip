package duke;

public class Deadline extends Task {
    private static final String DEADLINE_ICON = "[D]";

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString()
                + " (by: " + by + ")";
    }
}
