package duke.exception;

public class MissingParameter extends DukeException {

    @Override
    public String getMessage() {
        return "Your command is incomplete. Please try again.";
    }
}
