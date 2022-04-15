package duke.validation;

/**
 * This InvalidFileException class is an exception for when
 * the save file being read has unreadable text in it.
 */
public class InvalidFileException extends Exception{

    @Override
    public String toString() {
        return "Invalid Save File! Do not mess with my stuff!";
    }
}
