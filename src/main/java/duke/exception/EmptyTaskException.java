package duke.exception;

import duke.ui.Ui;

public class EmptyTaskException extends DukeException{
    @Override
    public void printErrorMessage(Ui ui) {
        ui.printTaskError(ui);
    }
}
