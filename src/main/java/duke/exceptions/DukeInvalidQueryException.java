package duke.exceptions;

public class DukeInvalidQueryException extends DukeException{
    public DukeInvalidQueryException() {
        errorMessage = "Please enter something to search for after 'find'";
    }
}
