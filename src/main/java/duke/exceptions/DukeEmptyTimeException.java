package duke.exceptions;

public class DukeEmptyTimeException extends DukeException{
    public DukeEmptyTimeException() {
        errorMessage = "Please enter the deadline/event time";
    }
}
