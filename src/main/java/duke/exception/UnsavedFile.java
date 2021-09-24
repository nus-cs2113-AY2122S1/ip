package duke.exception;

public class UnsavedFile extends DukeException {

    @Override
    public String getMessage() {
        return "The backup data file cannot be stored in your computer. All data will be lost once you close the program.";
    }

}

