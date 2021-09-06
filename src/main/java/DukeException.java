public class DukeException extends Exception {
    private String errorMessage;

    final static String HOR_LINE = "_".repeat(30);

    public DukeException(String message) {
        this.errorMessage = message;
    }

    public void printErrorMessage() {
        System.out.println(HOR_LINE);
        System.out.println(this.errorMessage);
        System.out.println(HOR_LINE);
    }
}
