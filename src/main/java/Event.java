public class Event extends Task {
    public static final char IDENTIFIER = 'E';
    private final String AT;

    Event(String description, String at) {
        super(description);
        this.AT = at;
    }

    @Override
    public String toString() {
        char completeCharacter = isComplete ? 'X' : ' ';
        return "[" + IDENTIFIER + "]" + "[" + completeCharacter + "] " + description + " (at: " + this.AT + ")";
    }
}
