package duke.exception;

/**
 * Prints exception message for trying to key in a todo with an invalid format
 */
public class TodoInvalidFormatException extends Exception
{
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    /**
     * Prints exception message including the correct format
     */
    public void printTodoInvalidFormatException()
    {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ mission format incorrect - try \"todo (description)\", Hero. ⚠️");
        System.out.println(MESSAGE_DIVIDER);
    }

}
