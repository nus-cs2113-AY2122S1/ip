package duke.exceptions;

public class InvalidFindFormatException extends DukeException {
    public InvalidFindFormatException() {
        message = "Wrong format for find!\nCorrect format: find <keyword>";
    }
}