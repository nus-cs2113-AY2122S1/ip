package duke.exception;

public class DoneInvalidFormatException extends Exception {
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_COMMAND_WRONG_FORMAT = " Your command is of the wrong format!";
    private static final String MESSAGE_DONE_CORRECT_FORMAT = " To mark a task as done, enter \"done {ID of done task}\".";

    public void printDoneInvalidFormatMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
        System.out.println(MESSAGE_DONE_CORRECT_FORMAT);
        System.out.println(OUTPUT_DIVIDER);
    }
}
