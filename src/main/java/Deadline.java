public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description, 'D');
        this.dueDate = dueDate;
    }
    public Deadline(String description,boolean isDone, String dueDate) {
        super(description, 'D',isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return "[" + taskType + "][" + getStatusIcon() + "] "
                + description + " (by: " + dueDate + ")";
    }

    public String getDueDate() {
        return dueDate;
    }
}
