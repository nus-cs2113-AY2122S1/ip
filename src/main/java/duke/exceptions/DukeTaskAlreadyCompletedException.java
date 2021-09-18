package duke.exceptions;

public class DukeTaskAlreadyCompletedException extends DukeException{
    public DukeTaskAlreadyCompletedException() {
        errorMessage = "This task is already completed";
    }
}
