package duke.exception;

public class CorruptedFile extends DukeException {

    @Override
    public String getMessage() {
        return "The backup data file is corrupted. A new backup file has been created.";
    }

}
