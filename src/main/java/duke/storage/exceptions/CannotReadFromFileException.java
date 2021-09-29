package duke.storage.exceptions;

import duke.ui.Ui;

/**
 * Represents an error where the system is unable to read the data file due to insufficient permissions.
 * Contains a fixed error message.
 */
public class CannotReadFromFileException extends Exception {
    private static final String ERROR_MESSAGE = "Error! System does not have sufficient permission to read data file?!" + Ui.LS
            + "Dude is unable to restore your task data. :(";

    public CannotReadFromFileException() {
        super(ERROR_MESSAGE);
    }

    /**
     * Returns the error message in String form.
     */
    @Override
    public String toString() {
        return ERROR_MESSAGE;
    }
}
