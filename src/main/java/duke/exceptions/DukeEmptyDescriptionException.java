package duke.exceptions;

public class DukeEmptyDescriptionException extends  DukeException{
    public DukeEmptyDescriptionException() {
        errorMessage = "Please enter a description of the task";
    }

}
