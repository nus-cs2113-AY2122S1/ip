package duke.exception;

public class DukeException extends Exception {
    protected  DukeException() {
        super();
    }
    public DukeException(String message) {
        super(message);
    }
}
