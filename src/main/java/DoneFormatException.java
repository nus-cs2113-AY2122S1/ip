public class DoneFormatException extends Exception {

    private final String DONE_INCORRECT_FORMAT_MSG = "\n[Duke]:\n"
            + "=> Yikes, your done command is wrong! Please follow the format:\n"
            + "   [\uD83D\uDCAC] 7. Set Task as Done -> {done <task ID>}";

    @Override
    public String toString() {
        return DONE_INCORRECT_FORMAT_MSG;
    }

}