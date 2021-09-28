package duke.exceptions;


/**
 * Thrown when the format of the <code>Deadline</code> task entered by the user is wrong.
 */
public class InvalidDeadlineFormatException extends DukeException {
    public InvalidDeadlineFormatException() {
        message = "\tWrong format for deadline!\n\tCorrect format: deadline <details> /by <time>";
    }
}