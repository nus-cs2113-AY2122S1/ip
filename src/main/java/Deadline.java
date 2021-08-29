public class Deadline extends Task {
    private String byTime;

    public static final String PREPOSITION = "by";

    public Deadline(String description, String byTime) {
        super(description);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (%s: %s)", super.toString(), PREPOSITION, byTime);
    }
}
