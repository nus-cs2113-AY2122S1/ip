public class Deadline extends Task {
    private String dueDate;
    public static final char TASK_TYPE_DEADLINE = 'D';

    public Deadline(String description, String dueDate) {
        super(description, TASK_TYPE_DEADLINE);
        this.dueDate = dueDate;
    }

    public Deadline(String description, boolean isDone, String dueDate) {
        super(description, TASK_TYPE_DEADLINE, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return "[" + taskType + "][" + getStatusIcon() + "] "
                + description + " (by: " + dueDate + ")";
    }

    public String getDate() {
        return dueDate;
    }
}
