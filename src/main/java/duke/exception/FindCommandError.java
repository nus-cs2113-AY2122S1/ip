package duke.exception;

public class FindCommandError extends DukeException {
    public FindCommandError() {
        this.errorMessage = "OH NO! The \"find\" command should be in this format: \nfind <search keyword>";
    }
}
