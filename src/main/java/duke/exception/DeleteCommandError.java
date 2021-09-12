package duke.exception;

public class DeleteCommandError extends DukeException{
    public DeleteCommandError() {
        this.errorMessage = "OH NO! \"delete\" command is to be followed by only one integer!";
    }
}
