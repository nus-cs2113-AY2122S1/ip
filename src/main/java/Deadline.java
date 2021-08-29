public class Deadline extends Task {
    public static final char TASK_TYPE = 'D';
    private final String BY;

    Deadline(String description, String by) {
        super(description);
        this.BY = by;
    }

    @Override
    public String toString() {
        char completeCharacter = isComplete ? 'X' : ' ';
        return "[" + TASK_TYPE + "]" + "[" + completeCharacter + "] " + description + " (by: " + this.BY + ")";
    }
}
