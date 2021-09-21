package duke.exception;

/**
 * Represents an exception that occurs when a command is not recognized as valid takes in a custom
 * message based on the command
 */
public class MissingArgumentException extends Exception {
    public MissingArgumentException(String msg) {
        super(msg);
    }
}
