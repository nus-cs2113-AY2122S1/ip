package duke;

public class Deadline extends Task {
    private String byDate;

    public Deadline(String taskName, String byDate) {
        super(taskName);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + byDate + ")";
    }
}
