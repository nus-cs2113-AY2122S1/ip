public class DukeException extends Exception {
    private String errorMessage;

    private final String ERROR_BOUNDARY = "\t" + "*!".repeat(30);

    public DukeException(String message) {
        this.errorMessage = message;
    }

    public void printErrorMessage() {
        System.out.println(ERROR_BOUNDARY);
        System.out.println(this.errorMessage);
        System.out.println(ERROR_BOUNDARY + System.lineSeparator());
    }
}
