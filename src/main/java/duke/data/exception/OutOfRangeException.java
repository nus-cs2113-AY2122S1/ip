package duke.data.exception;

public class OutOfRangeException extends DukeException {
    public OutOfRangeException() {
        super("The index is out of range");
    }

    public OutOfRangeException(String message) {
        super("The index is out of range" + message);
    }
}

