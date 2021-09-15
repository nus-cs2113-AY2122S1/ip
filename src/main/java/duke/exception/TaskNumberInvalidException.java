package duke.exception;

public class TaskNumberInvalidException extends Exception {
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    public void printTaskNumberInvalidException() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ mission ID must be numerical - try \"done (task number)\", Hero. ⚠️");
        System.out.println(MESSAGE_DIVIDER);
    }
}
