package exception;

public class NoDescriptionException extends Exception{

    private static final String MESSAGE = "The description of %s cannot be empty!";
    public NoDescriptionException(String userInput){
        super(String.format(MESSAGE, userInput.replaceAll("\\s+$", "")));
    }
}
