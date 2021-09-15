package duke.exception;

public class TodoInvalidFormatException extends Exception {
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    public void printTodoInvalidFormatException() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ mission format incorrect - try \"todo (description)\", Hero. ⚠️");
        System.out.println(MESSAGE_DIVIDER);
    }

}
