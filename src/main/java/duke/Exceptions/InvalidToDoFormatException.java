package duke.Exceptions;


public class InvalidToDoFormatException extends DukeException {
    public InvalidToDoFormatException() {
        message = "\tWrong format for todo!\n\tCorrect format: todo <details>";
    }
}
