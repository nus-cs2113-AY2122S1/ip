package duke.templar;

public class Deadline extends Task {
    protected String deadlineDate;

    public Deadline(String deadlineDescription, String deadlineDate) {
        super(deadlineDescription);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate + ") ";
    }
}
