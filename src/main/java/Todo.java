public class Todo extends Task {
    public static final char TASK_TYPE = 'T';

    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        char completeCharacter = isComplete ? 'X' : ' ';
        return "[" + TASK_TYPE + "]" + "[" + completeCharacter + "] " + description;
    }
}
