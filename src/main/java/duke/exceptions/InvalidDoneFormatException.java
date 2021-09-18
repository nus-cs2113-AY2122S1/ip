package duke.exceptions;


public class InvalidDoneFormatException extends DukeException {
    public InvalidDoneFormatException() {
        message = "\tWrong format for done!\n\tCorrect format: done <taskIndex>";
    }
}