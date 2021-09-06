package duke.exception;

public class DeadlineFormatException extends Exception {

    private final String DEADLINE_INCORRECT_FORMAT_MSG = "\n[Duke]:\n"
            + "=> Yikes, your deadline command is wrong! Please follow the format:\n"
            + "   [\uD83D\uDCAC] 5. Add Deadlines -> {deadline <task description> /by <task date&time>}";

    @Override
    public String toString() {
        return DEADLINE_INCORRECT_FORMAT_MSG;
    }

}
