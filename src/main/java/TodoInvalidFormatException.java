public class TodoInvalidFormatException extends Exception {

    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_COMMAND_WRONG_FORMAT = " Your command is of the wrong format!";
    public static final String MESSAGE_CORRECT_TODO_FORMAT = " To add a todo, enter \"todo {description}\".";

    public void printTodoInvalidFormatMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
        System.out.println(MESSAGE_CORRECT_TODO_FORMAT);
        System.out.println(OUTPUT_DIVIDER);
    }
}
