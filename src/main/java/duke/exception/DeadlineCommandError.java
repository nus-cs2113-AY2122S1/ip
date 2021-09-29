package duke.exception;

public class DeadlineCommandError extends DukeException {
    public DeadlineCommandError() {
        this.errorMessage = "OH NO! The \"deadline\" command should be in this format: \ndeadline <task name> /by <yyyy-mm-dd>";
    }
}
