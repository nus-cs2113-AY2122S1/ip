package duke.exception;

public class DeleteListIndexError extends DukeException{
    public DeleteListIndexError() {
        this.errorMessage = "OH NO! Invalid list index! Has the task at that index been added yet?";
    }
}
