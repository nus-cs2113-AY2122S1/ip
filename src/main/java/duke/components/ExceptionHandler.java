package duke.components;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyTaskException;
import duke.exceptions.IllegalDateTimeException;
import duke.exceptions.IllegalDeadlineException;
import duke.exceptions.IllegalEventException;

import static duke.constants.Messages.MESSAGE_DATE_TIME_EXCEPTION;
import static duke.constants.Messages.MESSAGE_DUKE_EXCEPTION;
import static duke.constants.Messages.MESSAGE_EMPTY_TASK_EXCEPTION;
import static duke.constants.Messages.MESSAGE_INVALID_DEADLINE_EXCEPTION;
import static duke.constants.Messages.MESSAGE_INVALID_EVENT_EXCEPTION;
import static duke.constants.Messages.MESSAGE_OTHER_EXCEPTION;

/**
 * A class that handles exceptions thrown by Duke.
 */
public class ExceptionHandler {

    private static Ui ui = new Ui();

    /**
     * Handles duke exceptions.
     *
     * @param e Exception of type DukeException thrown by Duke.
     */
    public static void handleDukeExceptions(DukeException e) {
        if (e instanceof IllegalDeadlineException) {
            ui.printMessage(MESSAGE_INVALID_DEADLINE_EXCEPTION);
        } else if (e instanceof IllegalEventException) {
            ui.printMessage(MESSAGE_INVALID_EVENT_EXCEPTION);
        } else if (e instanceof EmptyTaskException) {
            ui.printMessage(MESSAGE_EMPTY_TASK_EXCEPTION);
        } else if (e instanceof IllegalDateTimeException) {
            ui.printMessage(MESSAGE_DATE_TIME_EXCEPTION);
        } else {
            ui.printMessage(MESSAGE_DUKE_EXCEPTION);
        }
    }

    /**
     * Handles other exceptions.
     *
     * @param e Exception of types other than DukeException thrown by Duke.
     */
    public static void handleOtherExceptions(Exception e) {
        ui.printMessage(MESSAGE_OTHER_EXCEPTION);
    }
}
