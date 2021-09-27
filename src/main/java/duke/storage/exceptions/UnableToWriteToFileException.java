package duke.storage.exceptions;

import static duke.ui.Ui.LS;

public class UnableToWriteToFileException extends Exception {

    private static final String ERROR_MESSAGE = "Error! System does not have sufficient permission to write to data file?!"
            + LS + "Dude is unable to store your task data locally. :(";

    public UnableToWriteToFileException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
