package duke.task;

public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String description, String dueDate, boolean isDone) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

    @Override
    public String formatSaveToFile() {
        return "D" + super.formatSaveToFile() + " | " + dueDate;
    }
}