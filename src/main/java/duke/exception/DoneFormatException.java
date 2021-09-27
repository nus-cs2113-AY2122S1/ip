package duke.exception;

/**
 * Represents an exception where the user's done command does not comply with the correct format.
 * Delete command correct format: done [task ID], where task ID must be an integer.
 */
public class DoneFormatException extends Exception {

    private final String DONE_INCORRECT_FORMAT_MSG = "Yikes, your done command is wrong!"
            + " Please follow the format:\n"
            + "   [?] 7. Set Task as Done -> {done <task ID>}";

    @Override
    public String toString() {
        return DONE_INCORRECT_FORMAT_MSG;
    }

}