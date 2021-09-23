package duke.exceptions;

import duke.ui.Ui;

public class DateTimeFormatException extends DukeException {

    @Override
    public void sendMessage() {
        System.out.println(Ui.HORIZONTAL_LINE_TOP
                + "\n ☹ OOPS!!! The date and timing format is wrong.\n "
                + "Please represent date and time as: dd-mm-yyyy hh:mm\n"
                + Ui.HORIZONTAL_LINE_BOTTOM);
    }

}
