package duke.task;

public class Deadline extends Task {

    final private static String FLAG_TYPE = "[D]";

    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate.trim();
    }

    @Override
    public String getStatusIcon() {
        return FLAG_TYPE + super.getStatusIcon();
    }

    /**
     * Get all information of the task.
     *
     * @return String containing all information of the task.
     */
    @Override
    public String getTaskInfo() {
        return getDescription() + " (by: " + dueDate + ")";
    }

    @Override
    public String toString() {
        return FLAG_TYPE + " | " + getDoneStatus() + " | " + this.getDescription() + " | " + dueDate;
    }
}
