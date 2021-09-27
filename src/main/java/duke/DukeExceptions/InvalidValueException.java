package duke.DukeExceptions;

import duke.Duke;

public class InvalidValueException extends DukeException {
    private String errorMessage;

    public InvalidValueException(String expectedMessage) {
        this.errorMessage = ("\t____________________________________________________________\n\t"
                + expectedMessage
                + "\n\t____________________________________________________________\n\t");
    }

    public String printMessage() {
        return this.errorMessage;
    }
}
