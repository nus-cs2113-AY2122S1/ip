package duke.exception;

public class NoDataFile extends DukeException {

    @Override
    public String getMessage() {
        return "Cannot find the backup data file. A new backup file has been created.";
    }
}
