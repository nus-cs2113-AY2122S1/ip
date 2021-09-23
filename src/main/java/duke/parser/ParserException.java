package duke.parser;

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
