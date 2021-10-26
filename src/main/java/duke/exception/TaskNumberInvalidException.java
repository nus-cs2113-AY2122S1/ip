package duke.exception;

/**
 * Prints exception message for trying to access a task using the wrong format
 */
public class TaskNumberInvalidException extends Exception
{
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    /**
     * Prints error message including the correct format
     */
    public void printTaskNumberInvalidException()
    {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ mission ID must be numerical - try \"done (task number)\", Hero. ⚠️");
        System.out.println(MESSAGE_DIVIDER);
    }
}
