package duke.error;

import duke.ui.DisplayManager;

/**
 * Class responsible for errors related to Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException object and sets the error message.
     * @param errorMessage String containing the error message to display.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return DisplayManager.DISPLAY_MESSAGE_INDENT + "☹ OOPS!!! " + getMessage();
    }
}
