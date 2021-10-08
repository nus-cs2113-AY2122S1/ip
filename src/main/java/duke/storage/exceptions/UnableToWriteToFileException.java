package duke.storage.exceptions;

import duke.ui.Ui;

/**
 * Represents an error where the system is unable to write to the data file due to insufficient permissions.
 * Contains a fixed error message.
 */
public class UnableToWriteToFileException extends Exception {

    private static final String ERROR_MESSAGE = "Error! System does not have sufficient permission to write to data file?!"
            + Ui.LS + "Dude is unable to store your task data locally. :(";

    public UnableToWriteToFileException() {
        super(ERROR_MESSAGE);
    }


    /**
     * Returns the error message in String form along with the invalid line of data.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
