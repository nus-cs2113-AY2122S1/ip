public class Deadline extends Task {
    public static final char TASK_TYPE = 'D';
    private final String BY;

    Deadline(String description, String by) {
        super(description);
        taskSignature = "deadline";
        BY = by;
    }

    @Override
    public String toString() {
        completeStatus = isComplete ? COMPLETE_CHARACTER : INCOMPLETE_CHARACTER;
        return "[" + TASK_TYPE + "]" + "[" + completeStatus + "] " + description + " (by: " + BY + ")";
    }

    @Override
    public String getEncodedFormat() {
        return Character.toString(completeStatus) + taskSignature + description + "/by" + BY;
    }
}
