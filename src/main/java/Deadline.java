public class Deadline extends Task {

    protected String deadline;

    public String getDeadline() {
        return deadline;
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String toString() {
        return ("[D][" + getStatusIcon() + "] " + description + " (by:" + deadline + ")");
    }
}