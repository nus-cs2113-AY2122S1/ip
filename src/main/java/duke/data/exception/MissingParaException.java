package duke.data.exception;

public class MissingParaException extends DukeException {
    public MissingParaException() {
        super("Some variable is missing");
    }

    public MissingParaException(String message) {
        super("Some variable is missing" + message);
    }
}
