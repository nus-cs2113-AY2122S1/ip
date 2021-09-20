package duke.task;

public class Deadlines extends Task {

    private final String byWhen;

    public Deadlines(String taskName, String by) {
        super(taskName);
        this.byWhen = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byWhen + ")";
    }

    @Override
    public String storageText () {
        return "D" + super.storageText() + "|" + byWhen;
    }
}
