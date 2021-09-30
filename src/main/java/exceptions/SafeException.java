package exceptions;

public class SafeException extends DukeException{

    public static final String LOAD_FAILED = "Corrupted task syntax from file, unable to parse";
    public static final String SAVE_FAILED = "\"Failed to save following task: \n";

    /**
     * Exception thrown when Task Safe fails to write to a file or load from a file
     * E.g corrupted file where tasks cannot be loaded
     * @param cause additional message to be logged when exception is thrown
     */
    public SafeException (String cause) {
        super ("SafeException: " + cause);
    }
}
