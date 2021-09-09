package FridayExceptions;

public class MissingKeyWordException extends Exception {
    private String keyword;
    public MissingKeyWordException(String word) {
        this.keyword = word;
    }
    public String getkeyWord() {
        return keyword;
    }
}
