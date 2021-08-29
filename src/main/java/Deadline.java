public class Deadline extends Task {
    public static final char IDENTIFIER = 'D';
    private String by;

    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        char completeCharacter = isComplete ? 'X' : ' ';
        return "[" + IDENTIFIER + "]" + "[" + completeCharacter + "] " + description + " (by: " + this.by + ")";
    }
}
