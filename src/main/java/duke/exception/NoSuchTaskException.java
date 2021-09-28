package duke.exception;

public class NoSuchTaskException extends Exception {
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    public void printNoSuchTaskException() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ no such active mission exists - try again, Hero. ⚠️");
    }
}
