public class DukeMissingKeywordException extends DukeException{
    String keyword;

    public DukeMissingKeywordException(String keyword) {
        this.keyword = keyword;
    }
}
