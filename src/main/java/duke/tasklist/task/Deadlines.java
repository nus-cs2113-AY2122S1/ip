package duke.tasklist.task;

public class Deadlines extends Task {
    protected String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String getDate() {
        return this.by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

