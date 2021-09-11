public abstract class DukeException extends Exception {
    private final String message;

    public DukeException(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
