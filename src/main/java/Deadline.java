public class Deadline extends Task {

    protected String dueDate;

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String toString() {
        return ("[D][" + getStatusIcon() + "] " + description + " (by: " + dueDate + ")");
    }
}