package exception;

public class AustinEmptyKeywordException extends AustinException {
    public AustinEmptyKeywordException() {
        super("Oops. You have missed out on the keyword.");
    }
}
