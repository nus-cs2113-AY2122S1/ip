package duke.exception;

public class EventInvalidFormatException extends Exception {
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_COMMAND_WRONG_FORMAT = " Your command is of the wrong format!";
    private static final String MESSAGE_CORRECT_EVENT_FORMAT = " To add an event, enter \"event {description} /at {event time}\".";

    public void printEventInvalidFormatMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
        System.out.println(MESSAGE_CORRECT_EVENT_FORMAT);
        System.out.println(OUTPUT_DIVIDER);
    }
}
