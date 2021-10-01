package duke.exception;

import duke.ui.Ui;

public class ErrorDeleteException extends DukeException{
    @Override
    public void printErrorMessage(Ui ui) {
        ui.printDeleteError(ui);
    }
}
