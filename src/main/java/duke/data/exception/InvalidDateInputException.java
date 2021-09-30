package duke.data.exception;

public class InvalidDateInputException extends DukeException {
    public InvalidDateInputException() {
        super("I'm sorry, but I don't understand the time format");
    }

    public InvalidDateInputException(String message) {
        super("I'm sorry, but I don't understand the time format" + message);
    }
}
