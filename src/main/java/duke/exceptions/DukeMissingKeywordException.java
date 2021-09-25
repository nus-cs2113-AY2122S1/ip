package duke.exceptions;

/**
 * Thrown when either the "/by" for deadline or "/at" for event is missing when creating a new event or
 * deadline
 */
public class DukeMissingKeywordException extends DukeException {
    /**
     * @param keyword keyword is either "/at" or "/by depending on whether an event or deadline was being added
     */
    public DukeMissingKeywordException(String keyword) {
        this.errorMessage = "No " + keyword + " detected, press enter to see command syntax";
    }
}
