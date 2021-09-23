package exception;

/**
 * Exception class created for this chatbot to handle errors not detected by default
 * exception classes.
 */
public class AustinException extends Exception {
    protected String message;

    public AustinException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
