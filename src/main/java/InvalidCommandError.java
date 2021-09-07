public class InvalidCommandError extends DukeException{
    public InvalidCommandError() {
        this.errorMessage = "OH NO! I do not know what command is that!";
    }
}