package main.java.duke.exceptions;

import main.java.duke.ui.Ui;

/** Custom exception to catch any unfamiliar command words in the user input. */
public class InvalidCommandException extends DukeException {

    public void sendErrorMessage() {
        System.out.println(Ui.HORIZONTAL_LINE_TOP
                + "\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + Ui.HORIZONTAL_LINE_BOTTOM);
    }

}
