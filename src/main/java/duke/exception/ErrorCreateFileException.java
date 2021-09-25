package duke.exception;

import duke.ui.Ui;

public class ErrorCreateFileException extends DukeException{

    @Override
    public void printErrorMessage(Ui ui) {
        ui.printCreateFileError(ui);
    }
}
