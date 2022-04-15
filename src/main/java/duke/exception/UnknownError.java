package duke.exception;

public class UnknownError extends DukeException {

    @Override
    public String getMessage() {
        return "There is an unknown error. Please restart this program.";
    }
}
