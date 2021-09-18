package duke.exception;

public class EmptyListException extends Exception {
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_LIST_EMPTY = " The list is empty! Add a task first.";

    public void printEmptyListMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_LIST_EMPTY);
        System.out.println(OUTPUT_DIVIDER);
    }
}
