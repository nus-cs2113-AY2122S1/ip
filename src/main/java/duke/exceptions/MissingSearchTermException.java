package duke.exceptions;

/**
 * Exception class for when user does not input a search term after the find command.
 * Extended from DukeException.
 */
public class MissingSearchTermException extends DukeException {
    /**
     * Returns an error message as a string.
     * @return error message
     */
    @Override
    public String getMessage() {
        return "A search term must come after the find command!";
    }
}
