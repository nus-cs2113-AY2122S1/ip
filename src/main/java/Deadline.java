public class Deadline extends Task{
    protected String type = "D";
    protected String by = "";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[" + this.type + "]" + "[" + this.done + "] " + description.split("/by ")[0] + " (by: " + by + ")";
    }
}
