package duke.validation;

public class InvalidInputException extends Exception{

    @Override
    public String toString() {
        return "I cannot read that input!";
    }
}
