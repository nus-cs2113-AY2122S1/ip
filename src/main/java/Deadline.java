/**
 * Deadline class that is a type of Task.
 */
public class Deadline extends Task {
    public static final char TASK_TYPE = 'D';
    private String by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description Details of a Deadline object.
     * @param by          Finishing time of the Deadline object.
     */
    public Deadline(String description, String by) {
        super(description);
        taskSignature = "deadline";
        this.by = by;
    }

    /**
     * Returns a String object representing the Deadline.
     *
     * @return The String representation of the Deadline.
     */
    @Override
    public String toString() {
        completeStatus = isComplete ? COMPLETE_CHARACTER : INCOMPLETE_CHARACTER;
        return "[" + TASK_TYPE + "]" + "[" + completeStatus + "] " + description + " (by: " + by + ")";
    }

    /**
     * Returns a String object representing the Deadline in a format that can be used to read and write
     * the Deadline from/to file.
     *
     * @return The String representation of the Deadline that be used to read and write from/to file.
     */
    @Override
    public String getEncodedFormat() {
        return Character.toString(completeStatus) + taskSignature + description + "/by" + by;
    }
}
