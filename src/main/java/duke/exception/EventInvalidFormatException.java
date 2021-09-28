package duke.exception;

/**
 * Prints exception message for trying to key in an event with an invalid format
 */
public class EventInvalidFormatException extends Exception
{
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    /**
     * Prints exception message including the correct format
     */
    public void printEventInvalidFormatException()
    {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ mission format incorrect - try \"event (description) /at (date & time)\", Hero. ⚠️");
        System.out.println(MESSAGE_DIVIDER);
    }
}
