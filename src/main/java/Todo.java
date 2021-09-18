/**
 * Todo class that is a type of Task.
 */
public class Todo extends Task {
    public static final char TASK_TYPE = 'T';

    /**
     * Constructor for the Todo class.
     *
     * @param description Details of a Todo object.
     */
    public Todo(String description) {
        super(description);
        taskSignature = "todo";
    }

    /**
     * Returns a String object representing the Todo.
     *
     * @return The String representation of the Todo.
     */
    @Override
    public String toString() {
        completeStatus = isComplete ? COMPLETE_CHARACTER : INCOMPLETE_CHARACTER;
        return "[" + TASK_TYPE + "]" + "[" + completeStatus + "] " + description;
    }

    /**
     * Returns a String object representing the Todo in a format that can be used to read and write
     * the Todo from/to file.
     *
     * @return The String representation of the Todo that be used to read and write from/to file.
     */
    @Override
    public String getEncodedFormat() {
        return Character.toString(completeStatus) + taskSignature + description;
    }
}
