package duke.exceptions;

public class MissingSearchTermException extends DukeException {

    @Override
    public String getMessage() {
        return "A search term must come after the find command!";
    }
}
