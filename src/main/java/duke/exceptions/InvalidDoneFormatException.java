package duke.exceptions;


/**
 * Thrown when the format of the <code>Done</code> task entered by the user is wrong.
 */
public class InvalidDoneFormatException extends DukeException {
    public InvalidDoneFormatException() {
        message = "\tWrong format for done!\n\tCorrect format: done <taskIndex>";
    }
}