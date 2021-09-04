package duke.exception;

public class DeadlineInvalidFormatException extends Exception {
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_COMMAND_WRONG_FORMAT = " Your command is of the wrong format!";
    private static final String MESSAGE_CORRECT_DEADLINE_FORMAT = " To add a deadline, enter \"deadline {description} /by {deadline}\".";

    public void printDeadlineInvalidFormatMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
        System.out.println(MESSAGE_CORRECT_DEADLINE_FORMAT);
        System.out.println(OUTPUT_DIVIDER);
    }
}
