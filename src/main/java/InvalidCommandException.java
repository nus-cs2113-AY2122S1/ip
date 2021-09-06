public class InvalidCommandException extends Exception {

    private final String INVALID_COMMAND_MSG = "\n[Duke]:\n"
            + "=> Yikes, I do not recognise your input! (refer to 'help' command)";

    @Override
    public String toString() {
        return INVALID_COMMAND_MSG;
    }
}
