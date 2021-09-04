package duke.exception;

public class NonNumericTaskIdException extends Exception {
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_COMMAND_WRONG_FORMAT = " Your command is of the wrong format!";
    private static final String MESSAGE_NUMERIC_TASK_ID = " Enter a number for your task ID.";

    public void printNonNumericTaskIdMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
        System.out.println(MESSAGE_NUMERIC_TASK_ID);
        System.out.println(OUTPUT_DIVIDER);
    }
}
