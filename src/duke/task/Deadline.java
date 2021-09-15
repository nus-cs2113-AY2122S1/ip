package duke.task;

public class Deadline extends Task {
    public static final String DEADLINE_ICON = "[D]";
    private String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public String exportTask() {
        return "D|" + super.getStatus() + "|" + super.toString() + "|" + dueDate + System.lineSeparator();
    }

    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString() + " (by: " + dueDate + ")";
    }
}
