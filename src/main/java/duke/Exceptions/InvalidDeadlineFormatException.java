package duke.Exceptions;


public class InvalidDeadlineFormatException extends DukeException{
    public InvalidDeadlineFormatException() {
        message = "\tWrong format for deadline!\n\tCorrect format: deadline <details> /by <time>";
    }
}