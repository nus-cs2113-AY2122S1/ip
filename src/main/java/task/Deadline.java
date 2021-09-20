package task;

public class Deadline extends Task {
    protected String byWhen;

    public Deadline(String taskName, String byWhen) {
        super(taskName);
        this.byWhen = byWhen;
    }

    public Deadline(String taskName, boolean isDone, String byWhen) {
        super(taskName, isDone);
        this.byWhen = byWhen;
    }

    public String getByWhen() {
        return byWhen;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By -> " + byWhen + ")";
    }
}
