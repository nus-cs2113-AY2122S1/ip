package duke.data.exception;

public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("I'm sorry, but I don't know what that means :-(");
    }

    public InvalidInputException(String message) {
        super("I'm sorry, but I don't know what that means :-(" + message);
    }
}
