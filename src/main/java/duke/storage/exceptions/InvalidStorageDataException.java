package duke.storage.exceptions;

import static duke.ui.Ui.LS;

public class InvalidStorageDataException extends Exception {

    private static String errorMessage =
            "Error restoring data due to invalid syntax! This line of data will not be added:" + LS
            + "%s";

    private String invalidEncodedTask;

    public InvalidStorageDataException(String encodedTask) {
        super(String.format(errorMessage, encodedTask));
        this.invalidEncodedTask = encodedTask;
    };

    @Override
    public String toString() {
        return String.format(errorMessage, invalidEncodedTask);
    }
}
