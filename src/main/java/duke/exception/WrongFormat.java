package duke.exception;

public class WrongFormat extends DukeException {

    @Override
    public String getMessage() {
        return "Your command format is wrong. Please try again.";
    }
}
