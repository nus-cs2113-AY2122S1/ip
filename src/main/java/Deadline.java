public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        if (by.equals("")) {
            this.by = "???";
        } else {
            this.by = by;
        }
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String getTaskDescriptionWithStatus() {
        return "[D]" + super.getTaskDescriptionWithStatus() + " (by: " + by + ")";
    }
}
