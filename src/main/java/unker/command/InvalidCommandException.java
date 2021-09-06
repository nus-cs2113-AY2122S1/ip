package unker.command;

public class InvalidCommandException extends Exception {
    
    private final Command command;
    
    public InvalidCommandException(String message) {
        this(message, null);
    }
    
    public InvalidCommandException(String message, Command command) {
        super(message);
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
