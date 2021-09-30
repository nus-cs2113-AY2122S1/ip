package FridayExceptions;

public class MissingIndexException extends Exception {
    private String command;
    public MissingIndexException(String command) {
        this.command = command;
    }
    public String getCommand() {
        return command;
    }
}
