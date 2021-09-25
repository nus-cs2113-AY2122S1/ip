package duke.exception;

import duke.ui.Ui;

public class ErrorDoneException extends DukeException{
    @Override
    public void printErrorMessage(Ui ui) {
        ui.printDoneError(ui);
    }
}
