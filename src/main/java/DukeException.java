public class DukeException extends Exception {

    protected static String errorMessage;

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static String getErrorMessage() {
        return errorMessage;
    }
}
