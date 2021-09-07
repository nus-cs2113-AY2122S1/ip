package duke.exception;

public class DeadlineInvalidFormatException extends Exception {
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    public void printDeadlineInvalidFormatException() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ mission format incorrect - try \"deadline (description) /by (date & time)\", Hero. ⚠️");
        System.out.println(MESSAGE_DIVIDER);
    }
}
