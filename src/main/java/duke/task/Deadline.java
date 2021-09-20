package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        super.setDateAndTime(by);
    }

    public String getBy() {
        return by;
    }

    @Override
    public void setDateAndTime(String line) {
        super.setDateAndTime(line);
    }

    @Override
    public String toString() {
        if (hasDateTime) {
            by = getDateAndTime();
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
