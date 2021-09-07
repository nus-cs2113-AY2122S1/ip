package duke.exception;

public class EventCommandError extends DukeException{
    public EventCommandError() {
        this.errorMessage = "OH NO! The \"event\" command should be in this format: \nevent <task name> /by <date>";
    }
}
