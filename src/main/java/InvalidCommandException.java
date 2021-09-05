public class InvalidCommandException extends Exception{

    String invalidCommand;

    InvalidCommandException(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    @Override
    public String toString() {
        return invalidCommand;
    }
}
