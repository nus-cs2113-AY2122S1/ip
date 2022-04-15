package exception;

/** Thrown when the keyword is missing after user keys in the "find" command */
public class AustinEmptyKeywordException extends AustinException {
    public AustinEmptyKeywordException() {
        super("Oops. You have missed out on the keyword.");
    }
}
