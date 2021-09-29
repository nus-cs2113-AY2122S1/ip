package duke.exception;

public class NumberOutOfBoundsException extends Exception {
    @Override
    public String getMessage() {
        return " OOPS! There is no task with this number: ";
    }
}
