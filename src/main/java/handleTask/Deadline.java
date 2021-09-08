public class Deadlines extends Task {
    private String dueDate;

    public Deadlines(String description, String dueDate) {
        super(description, 'D');
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return "[" + taskType + "][" + getStatusIcon() + "]"
                + description + " (by:" + dueDate + ")";
    }
}
