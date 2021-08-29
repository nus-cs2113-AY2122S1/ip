public class Deadline extends Task {

    private static final String SYMBOL = "D";
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + deadline + ")";
    }
}
