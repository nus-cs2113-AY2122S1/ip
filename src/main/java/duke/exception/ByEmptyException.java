package duke.exception;

public class ByEmptyException extends Exception {
    @Override
    public String getMessage() {
        return " \"by:\" cannot be empty.";
    }
}
