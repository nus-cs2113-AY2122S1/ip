package duke.exception;

public class DoneCommandError extends DukeException{
    public DoneCommandError() {
        this.errorMessage = "OH NO! \"done\" command is to be followed by only one integer!";
    }
}
