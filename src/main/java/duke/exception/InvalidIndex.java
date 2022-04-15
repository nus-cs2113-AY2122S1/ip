package duke.exception;

public class InvalidIndex extends DukeException {

    @Override
    public String getMessage() {
        return "The index number you keyed in is invalid.";
    }

}
