/**
 * Event class that is a type of Task.
 */
public class Event extends Task {
    public static final char TASK_TYPE = 'E';
    private String at;

    /**
     * Constructor for the Event class.
     *
     * @param description Details of an Event object.
     * @param at          Finishing time of the Event object.
     */
    public Event(String description, String at) {
        super(description);
        taskSignature = "event";
        this.at = at;
    }

    /**
     * Returns a String object representing the Event.
     *
     * @return The String representation of the Event.
     */
    @Override
    public String toString() {
        completeStatus = isComplete ? COMPLETE_CHARACTER : INCOMPLETE_CHARACTER;
        return "[" + TASK_TYPE + "]" + "[" + completeStatus + "] " + description + " (at: " + at + ")";
    }

    /**
     * Returns a String object representing the Event in a format that can be used to read and write
     * the Event from/to file.
     *
     * @return The String representation of the Event that be used to read and write from/to file.
     */
    @Override
    public String getEncodedFormat() {
        return Character.toString(completeStatus) + taskSignature + description + "/at" + at;
    }
}
