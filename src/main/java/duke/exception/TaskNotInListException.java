package duke.exception;

public class TaskNotInListException extends Exception {
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_TASK_NOT_IN_LIST = " Sorry, the task is not in the list! Try again.";

    public void printTaskNotInListMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_TASK_NOT_IN_LIST);
        System.out.println(OUTPUT_DIVIDER);
    }
}
