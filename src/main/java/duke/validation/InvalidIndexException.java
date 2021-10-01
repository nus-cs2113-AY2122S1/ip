package duke.validation;

public class InvalidIndexException extends Exception{

    @Override
    public String toString() {
        return "There is not task with that index!";
    }
}
