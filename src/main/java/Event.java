public class Event extends Task {
    public static final char TASK_TYPE = 'E';
    private final String AT;

    Event(String description, String at) {
        super(description);
        taskSignature = "event";
        AT = at;
    }

    @Override
    public String toString() {
        completeStatus = isComplete ? COMPLETE_CHARACTER : INCOMPLETE_CHARACTER;
        return "[" + TASK_TYPE + "]" + "[" + completeStatus + "] " + description + " (at: " + AT + ")";
    }

    @Override
    public String getEncodedFormat() {
        return Character.toString(completeStatus) + taskSignature + description + "/at" + AT;
    }
}
