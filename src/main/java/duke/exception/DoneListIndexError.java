package duke.exception;

public class DoneListIndexError extends DukeException{
    public DoneListIndexError() {
        this.errorMessage = "OH NO! Invalid list index! Has the task at that index been added yet?";
    }
}
