package duke.exceptions;


public class DukeException extends Exception {
    protected String message;

    public String getMessage() {
        return message;
    }
}