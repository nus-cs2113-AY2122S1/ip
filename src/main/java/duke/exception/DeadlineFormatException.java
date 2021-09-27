package duke.exception;

/**
 * Represents an exception where the user's Deadline command does not comply with the correct format.
 * Deadline command correct format: deadline [task description] /by [task date&time].
 */
public class DeadlineFormatException extends Exception {

    private final String DEADLINE_INCORRECT_FORMAT_MSG = "Yikes, your deadline command is wrong!"
            + " Please follow the format:\n"
            + "   [?] 5. Add Deadlines -> {deadline <task description> /by <task date&time>}";

    @Override
    public String toString() {
        return DEADLINE_INCORRECT_FORMAT_MSG;
    }

}
