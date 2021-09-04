package duke.exception;

public class EventLacksArgumentsException extends Exception {
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_COMMAND_LACKS_ARG = " Your command lacks an argument!";
    private static final String MESSAGE_CORRECT_EVENT_FORMAT = " To add an event, enter \"event {description} /at {event time}\".";

    public void printEventLacksArgumentsMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_COMMAND_LACKS_ARG);
        System.out.println(MESSAGE_CORRECT_EVENT_FORMAT);
        System.out.println(OUTPUT_DIVIDER);
    }
}
