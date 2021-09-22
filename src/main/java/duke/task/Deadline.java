package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }

    @Override
    public String getStoreDataString() {
        String checkDone = isDone ? "1" : "0";
        return "D | " + checkDone + " | " + description + " | " + by;
    }
}
