package duke.exception;

public class DukeException extends Exception {

    public DukeException() {
        super("DukeException");
    }

    public DukeException(String message) {
        super(message);
    }

}
