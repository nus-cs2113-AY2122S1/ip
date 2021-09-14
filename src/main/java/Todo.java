public class Todo extends Task {
    public static final char TASK_TYPE = 'T';

    Todo(String description) {
        super(description);
        taskSignature = "todo";
    }

    @Override
    public String toString() {
        completeStatus = isComplete ? COMPLETE_CHARACTER : INCOMPLETE_CHARACTER;
        return "[" + TASK_TYPE + "]" + "[" + completeStatus + "] " + description;
    }

    @Override
    public String getEncodedFormat() {
        return Character.toString(completeStatus) + taskSignature + description;
    }
}
