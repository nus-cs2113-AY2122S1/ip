package duke.exception;

/**
 * Prints exception message for trying to key in a deadline with an invalid format
 */
public class DeadlineInvalidFormatException extends Exception
{
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    /**
     * Prints exception message including the correct format
     */
    public void printDeadlineInvalidFormatException()
    {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ mission format incorrect - try \"deadline (description) /by (date & time)\", Hero. ⚠️");
        System.out.println(MESSAGE_DIVIDER);
    }
}
