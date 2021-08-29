public class Deadline extends Task {
    public static final char IDENTIFIER = 'D';
    private final String BY;

    Deadline(String description, String by) {
        super(description);
        this.BY = by;
    }

    @Override
    public String toString() {
        char completeCharacter = isComplete ? 'X' : ' ';
        return "[" + IDENTIFIER + "]" + "[" + completeCharacter + "] " + description + " (by: " + this.BY + ")";
    }
}
