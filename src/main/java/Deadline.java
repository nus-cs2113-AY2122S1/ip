public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getIcon() {
        return "[D]";
    }

    public String getBy() {
        return "(by:" + by + ")";
    }

    @Override
    public String toString() {
        return getIcon() + super.toString() + "(by:" + by + ")";
    }
}