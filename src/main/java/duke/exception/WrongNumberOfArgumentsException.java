package duke.exception;

public class WrongNumberOfArgumentsException extends Exception {
    private final static String MESSAGE = "Wrong arguments. Usage: %s <action> /%s <datetime>";

    public WrongNumberOfArgumentsException(String type, String preposition) {
        super(String.format(MESSAGE, type, preposition));
    }
    public WrongNumberOfArgumentsException() {
        super();
    }
}
