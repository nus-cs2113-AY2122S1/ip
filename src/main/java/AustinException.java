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
