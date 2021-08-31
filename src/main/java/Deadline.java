public class Deadline extends Task {
    protected String deadline;
    protected static final String DEADLINE_LOGO = "[E] ";

    public Deadline(String description) {
        super(description);
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return DEADLINE_LOGO + super.toString() + " (by: " + deadline + " )";
    }
}
