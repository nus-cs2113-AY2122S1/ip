public class Deadline extends Task {
    protected String byWhen;

    public Deadline(String taskName, String byWhen) {
        super(taskName);
        this.byWhen = byWhen;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By -> " + byWhen + ")";
    }
}
