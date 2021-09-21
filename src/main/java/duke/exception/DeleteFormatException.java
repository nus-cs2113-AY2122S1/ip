package duke.exception;

public class DeleteFormatException extends Exception{

    private final String DELETE_INCORRECT_FORMAT_MSG = "Yikes, your delete command is wrong!"
            + " Please follow the format:\n"
            + "   [\uD83D\uDCAC] 8. Delete Task -> {delete <task ID>}";

    @Override
    public String toString() {
        return DELETE_INCORRECT_FORMAT_MSG;
    }

}
