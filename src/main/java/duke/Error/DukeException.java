package duke.Error;

import duke.Ui.DisplayManager;

public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return DisplayManager.DISPLAY_MESSAGE_INDENT + "☹ OOPS!!! " + getMessage();
    }
}
