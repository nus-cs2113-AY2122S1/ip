public class Deadline extends Task {

    private final String symbol = "D";
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[" + symbol + "]" + super.toString() + " (by: " + deadline + ")";
    }
}
