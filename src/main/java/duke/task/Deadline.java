package duke.task;

public class Deadline extends Task {

    private static final String DEADLINE_ITEM = "[D] ";

    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String printBy() {
        return " (by: " + dueDate + ")";
    }

    @Override
    public String toString() {
        return DEADLINE_ITEM + super.toString() + printBy();
    }
}
