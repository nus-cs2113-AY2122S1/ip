package duke.exceptions;

public class DukeMissingKeywordException extends DukeException {
    private String keyword;

    public DukeMissingKeywordException(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
