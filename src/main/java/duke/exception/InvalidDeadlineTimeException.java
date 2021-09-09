package duke.exception;

public class InvalidDeadlineTimeException extends Exception {
    public InvalidDeadlineTimeException() {
        super();
    }

    public InvalidDeadlineTimeException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "Ops! Please add time for Deadline using the keyword '/by'!";
    }
}
