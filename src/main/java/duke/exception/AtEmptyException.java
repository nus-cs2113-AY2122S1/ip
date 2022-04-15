package duke.exception;

public class AtEmptyException extends Exception {
    @Override
    public String getMessage() {
        return " \"at:\" cannot be empty.";
    }
}
