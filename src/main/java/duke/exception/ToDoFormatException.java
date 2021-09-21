package duke.exception;

public class TodoFormatException extends Exception {

    private final String TODO_INCORRECT_FORMAT_MSG = "\n[Duke]:\n"
            + "=> Yikes, your todo command is wrong! Please follow the format:\n"
            + "   [\uD83D\uDCAC] 4. Add Todos -> {todo <task description>}";

    @Override
    public String toString() {
        return TODO_INCORRECT_FORMAT_MSG;
    }

}
