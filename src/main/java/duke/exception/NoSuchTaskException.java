package duke.exception;

/**
 * Prints exception message for trying to find/mark as complete a non-existent task
 */
public class NoSuchTaskException extends Exception
{
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    /**
     * Prints exception message to prompt user to try a different task number
     */
    public void printNoSuchTaskException()
    {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ no such active mission exists - try again, Hero. ⚠️");
    }
}
