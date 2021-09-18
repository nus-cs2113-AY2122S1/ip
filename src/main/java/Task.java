public class Task {
    protected String description;
    protected boolean isComplete;
    protected char completeStatus;
    protected String taskSignature;
    public static final char COMPLETE_CHARACTER = 'X';
    public static final char INCOMPLETE_CHARACTER = ' ';

    Task(String inputTask) {
        description = inputTask;
        isComplete = false;
        completeStatus = INCOMPLETE_CHARACTER;
        taskSignature = "task";
    }

    void markComplete() {
        isComplete = true;
        completeStatus = COMPLETE_CHARACTER;
    }

    String getEncodedFormat() {
        return Character.toString(completeStatus) + description;
    }
}

