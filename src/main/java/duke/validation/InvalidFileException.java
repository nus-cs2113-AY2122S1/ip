package duke.validation;

public class InvalidFileException extends Exception{

    @Override
    public String toString() {
        return "Invalid Save File! Do not mess with my stuff!";
    }
}
