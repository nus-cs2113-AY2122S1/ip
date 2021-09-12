package duke.exception;

public class FileTaskInvalidFormatException extends Exception {
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_FILE_TASK_WRONG_FORMAT = " Task loaded from file is of the wrong format!";

    public void printFileTaskInvalidFormatMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_FILE_TASK_WRONG_FORMAT);
        System.out.println(OUTPUT_DIVIDER);
    }
}
