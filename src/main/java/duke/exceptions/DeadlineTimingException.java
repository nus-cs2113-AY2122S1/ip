package main.java.duke.exceptions;

import main.java.duke.ui.Ui;

/** Custom exception to catch missing deadline timings in the user input. */
public class DeadlineTimingException extends DukeException {

    @Override
    public void sendErrorMessage() {
        System.out.println(Ui.HORIZONTAL_LINE_TOP
                + "\n ☹ OOPS!!! The deadline timing is missing.\n"
                + Ui.HORIZONTAL_LINE_BOTTOM);
    }

}
