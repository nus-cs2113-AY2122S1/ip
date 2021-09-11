package duke.tasks;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toData() {
        return "D | " + ((isDone) ? 1 : 0) + " | " + task + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + task + " (by: " + by + ")";
    }

}
