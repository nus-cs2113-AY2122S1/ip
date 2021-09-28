package duke.task;

public class Deadline extends Task {

    protected String by;
    protected String type = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getMoreDetails() {
        return this.by;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}