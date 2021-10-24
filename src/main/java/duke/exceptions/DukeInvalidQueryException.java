package duke.exceptions;

/**
 * Thrown when there is no query after "find" found in the user input
 */
public class DukeInvalidQueryException extends DukeException {
    public DukeInvalidQueryException() {
        errorMessage = "Please enter something to search for after 'find'";
    }
}
