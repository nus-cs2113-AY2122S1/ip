package duke.exceptions;

public class DukeMissingKeywordException extends DukeException {
    private String keyword;

    public DukeMissingKeywordException(String keyword) {
        this.keyword = keyword;
        this.errorMessage = "No " + keyword + " detected, press enter to see command syntax";
    }
}
