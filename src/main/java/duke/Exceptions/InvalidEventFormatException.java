package duke.Exceptions;


/**
 * Thrown when the format of the <code>Event</code> task entered by the user is wrong.
 */
public class InvalidEventFormatException extends DukeException {
    public InvalidEventFormatException() {
        message = "\tWrong format for event!\n\tCorrect format: event <details> /at <location>";
    }
}