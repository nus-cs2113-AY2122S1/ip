package duke.Exceptions;


/**
 * Thrown when the format of the <code>Delete</code> task entered by the user is wrong.
 */
public class InvalidDeleteFormatException extends DukeException {
    public InvalidDeleteFormatException() {
        message = "\tWrong format for delete!\n\tCorrect format: delete <taskIndex>";
    }
}
