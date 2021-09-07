package duke.exception;

public class CommandInvalidException extends Exception {
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    public void printCommandInvalidException() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ no such command exists - try \"print commands\" to see list of valid commands, Hero. ⚠️");
        System.out.println(MESSAGE_DIVIDER);
    }
}
