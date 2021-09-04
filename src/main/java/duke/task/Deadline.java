package duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String descr, String by) {
        super(descr);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
