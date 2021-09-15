package duke.exception;

public class EventInvalidFormatException extends Exception {
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    public void printEventInvalidFormatException() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ mission format incorrect - try \"event (description) /at (date & time)\", Hero. ⚠️");
        System.out.println(MESSAGE_DIVIDER);
    }
}
