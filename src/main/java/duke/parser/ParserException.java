package duke.parser;

/**
 * Parser Exception class that handles exception such as invalid commands from user inputs.
 */
public class ParserException extends Exception {

    private String errorMessage;

    public ParserException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
