package duke.validation;

public class InvalidFormatException extends Exception{

    @Override
    public String toString() {
        return "You're input is missing some important details!";
    }
}
