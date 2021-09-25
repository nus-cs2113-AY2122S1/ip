package duke.exception;
import duke.ui.Ui;
public class DukeException extends Exception {

    public void printErrorMessage(Ui ui) {
        ui.printDukeException(ui);
    }
}
