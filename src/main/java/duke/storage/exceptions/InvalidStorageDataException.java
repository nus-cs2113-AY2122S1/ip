package duke.storage.exceptions;

import duke.ui.Ui;

/**
 * Represents an error in decoding the data from the storage file into its corresponding Task due to invalid syntax.
 * This is a possible error if the user edits the storage file by themselves without following the correct syntax.
 * Contains a fixed error message.
 */
public class InvalidStorageDataException extends Exception {

    private static String errorMessage =
            "Error restoring data due to invalid syntax! This line of data will not be added:" + Ui.LS
            + "%s";

    private String invalidEncodedTask;

    public InvalidStorageDataException(String encodedTask) {
        super(String.format(errorMessage, encodedTask));
        this.invalidEncodedTask = encodedTask;
    };

    /**
     * Returns the error message in String form along with the invalid line of data.
     */
    @Override
    public String toString() {
        return String.format(errorMessage, invalidEncodedTask);
    }
}
