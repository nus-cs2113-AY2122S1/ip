package duke.task;

public class Deadline extends Task {
    private String deadlineDate;

    public Deadline(String description) {
        super(description.substring(9, description.indexOf(" /by")));
        this.deadlineDate = description.substring(description.indexOf("/by") + 4);
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadlineDate + ")";
    }
}
