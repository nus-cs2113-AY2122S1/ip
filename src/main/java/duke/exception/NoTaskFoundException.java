package duke.exception;

/**
 * Represents an exception where the find command produces no results with the supplied keyword by the user.
 */
public class NoTaskFoundException extends Exception {

    private final String NO_TASK_FOUND_MSG = "Yikes, the keyword supplied returned 0 results!\n"
            + "=> Please enter another keyword...";

    @Override
    public String toString() {
        return NO_TASK_FOUND_MSG;
    }

}
