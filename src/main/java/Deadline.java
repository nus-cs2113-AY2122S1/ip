public class Deadline extends Task{
    protected String by;
    protected String originalInput;
    public Deadline(String description, String by, String originalInput) {
        super(description);
        this.by = by;
        this.originalInput = originalInput;
    }

    public String getOriginalInput() {
        return originalInput;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String getType() {
        return "D";
    }

    public String toString() {
        return "[D]" + super.toString() + " (" + by + ")";
    }
}
