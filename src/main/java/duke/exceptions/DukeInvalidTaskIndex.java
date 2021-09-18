package duke.exceptions;

public class DukeInvalidTaskIndex extends DukeException{
    public DukeInvalidTaskIndex() {
        errorMessage = "Please enter valid task index number";
    }
}
