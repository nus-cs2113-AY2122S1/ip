package duke.Exceptions;


public class InvalidDeleteFormatException extends DukeException {
    public InvalidDeleteFormatException() {
        message = "\tWrong format for delete!\n\tCorrect format: delete <taskIndex>";
    }
}
