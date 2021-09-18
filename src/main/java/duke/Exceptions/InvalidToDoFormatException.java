package duke.Exceptions;


/**
 * Thrown when the format of the <code>ToDo</code> task entered by the user is wrong.
 */
public class InvalidToDoFormatException extends DukeException {
    public InvalidToDoFormatException() {
        message = "\tWrong format for todo!\n\tCorrect format: todo <details>";
    }
}
