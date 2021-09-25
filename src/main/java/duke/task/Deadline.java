package duke.task;

public class Deadline extends Task{
    protected String by;
    private static final String ICON_DEADLINE = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskIcon() {
        return ICON_DEADLINE;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskIcon() +"]" + super.toString() + " by: " + by;
    }

    @Override
    public String getDue() {
        return by;
    }
}
