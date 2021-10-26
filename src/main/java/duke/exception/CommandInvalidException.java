package duke.exception;

/**
 * Prints exception message for trying to input an invalid command
 */
public class CommandInvalidException extends Exception
{
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";

    /**
     * Prints exception message to the command line to suggest to the user to view the valid commands of this program
     */
    public void printCommandInvalidException()
    {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("⚠️ no such command exists - try \"print commands\" to see list of valid commands, Hero. ⚠️");
        System.out.println(MESSAGE_DIVIDER);
    }
}
