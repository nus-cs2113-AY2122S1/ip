public class Deadline extends Task{
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTime() {
        return this.by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getType() {
        return "D";
    }

    public String toString() {
        return "[D]" + super.getStatus() + super.getDescription() + " (by:" + by + ")";
    }
}
