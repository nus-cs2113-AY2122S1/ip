package duke.exception;

public class EmptyCommandError extends DukeException{
    public EmptyCommandError() {
        this.errorMessage = "OH NO! You have to enter at least something!";
    }
}
