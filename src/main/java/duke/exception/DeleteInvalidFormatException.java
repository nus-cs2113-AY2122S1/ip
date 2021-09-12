package duke.exception;

public class DeleteInvalidFormatException extends Exception {
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_COMMAND_WRONG_FORMAT = " Your command is of the wrong format!";
    private static final String MESSAGE_DELETE_CORRECT_FORMAT = " To delete a task, enter \"delete {ID of done task}\".";

    public void printDeleteInvalidFormatMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
        System.out.println(MESSAGE_DELETE_CORRECT_FORMAT);
        System.out.println(OUTPUT_DIVIDER);
    }
}
