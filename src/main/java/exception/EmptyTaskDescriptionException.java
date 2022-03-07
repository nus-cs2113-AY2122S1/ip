package exception;

public class EmptyTaskDescriptionException extends Exception {
    public EmptyTaskDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
