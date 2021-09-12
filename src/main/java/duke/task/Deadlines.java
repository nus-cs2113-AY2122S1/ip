package duke.task;

public class Deadlines extends Task {

    protected String by;

    public Deadlines(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String storageText () {
        return "D" + super.storageText() + "|" + by;
    }
}
