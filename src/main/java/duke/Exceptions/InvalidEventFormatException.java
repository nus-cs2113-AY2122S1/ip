package duke.Exceptions;


public class InvalidEventFormatException extends DukeException{
    public InvalidEventFormatException() {
        message = "\tWrong format for event!\n\tCorrect format: event <details> /at <location>";
    }
}