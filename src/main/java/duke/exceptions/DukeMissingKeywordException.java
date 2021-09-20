package duke.exceptions;

public class DukeMissingKeywordException extends DukeException {
    public DukeMissingKeywordException(String keyword) {
        this.errorMessage = "No " + keyword + " detected, press enter to see command syntax";
    }
}
