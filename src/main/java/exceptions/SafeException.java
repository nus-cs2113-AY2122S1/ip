package exceptions;

public class SafeException extends DukeException{

    public static final String LOAD_FAILED = "Corrupted task syntax from file, unable to parse";
    public static final String SAVE_FAILED = "\"Failed to save following task: \n";

    public SafeException (String cause) {
        super ("SafeException: " + cause);
    }
}
