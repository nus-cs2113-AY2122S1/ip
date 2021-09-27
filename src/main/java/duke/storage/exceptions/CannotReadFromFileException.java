package duke.storage.exceptions;

import static duke.ui.Ui.LS;

public class CannotReadFromFileException extends Exception {
    private static final String ERROR_MESSAGE = "Error! System does not have sufficient permission to read data file?!" + LS
            + "Dude is unable to restore your task data. :(";

    public CannotReadFromFileException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
