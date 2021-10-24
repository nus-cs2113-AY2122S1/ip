package duke.exceptions;

/**
 * This exception is thrown when the time-field while adding a deadline or event is missing
 */
public class DukeEmptyTimeException extends DukeException {
    public DukeEmptyTimeException() {
        errorMessage = "Please enter the deadline/event time";
    }
}
