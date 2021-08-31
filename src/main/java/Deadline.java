public class Deadline extends Task {
    protected String deadline;
    protected static final String DEADLINE_LOGO = "[D]";

    public Deadline(String description, String deadline) {
        super(description);
        setDeadline(deadline);
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return DEADLINE_LOGO + super.toString() + " (by: " + deadline + ")";
    }
}
