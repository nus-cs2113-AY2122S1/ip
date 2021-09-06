package duke.validation;

import duke.UI;

public class DukeException extends Exception{

    public static void invalidInputException() {
        UI.printInvalidMessage();
    }

    public static void invalidTodoException() {
        UI.printInvalidTodoMessage();
    }

    public static void invalidDeadlineException() {
        UI.printInvalidDeadlineMessage();
    }

    public static void invalidEndDateException() {
        UI.printInvalidEndDateMessage();
    }

    public static void invalidEventException() {
        UI.printInvalidEventMessage();
    }

    public static void invalidDurationException() {
        UI.printInvalidDurationMessage();
    }

    public static void invalidCrossOffException() {
        UI.printInvalidCrossOffMessage();
    }

    public static void invalidTaskIndexException() {
        UI.printInvalidTaskIndexMessage();
    }
}
