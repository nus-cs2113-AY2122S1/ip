/**
 * Task abstract class to be the parent of Event, Deadline, Todo classes
 */
public abstract class Task {
    protected String description;
    protected boolean isComplete;
    protected char completeStatus;
    protected String taskSignature;
    public static final char COMPLETE_CHARACTER = 'X';
    public static final char INCOMPLETE_CHARACTER = ' ';

    /**
     * Constuctor for the Task class
     *
     * @param inputTask String description of a Task
     */
    public Task(String inputTask) {
        description = inputTask;
        isComplete = false;
        completeStatus = INCOMPLETE_CHARACTER;
        taskSignature = "task";
    }

    /**
     * Sets a Task to be completed
     */
    public void markComplete() {
        isComplete = true;
        completeStatus = COMPLETE_CHARACTER;
    }

    /**
     * Returns a String object representing the Task in a format that can be used to read and write
     * the Task from/to file. It is overridden for each child of the Task class.
     *
     * @return The String representation of the Task that be used to read and write from/to file.
     */
    public abstract String getEncodedFormat();
}
