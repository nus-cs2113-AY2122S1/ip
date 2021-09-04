public class DeadlineLacksArgumentsException extends Exception {
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_COMMAND_LACKS_ARG = " Your command lacks an argument!";
    private static final String MESSAGE_CORRECT_DEADLINE_FORMAT = " To add a deadline, enter \"deadline {description} /by {deadline}\".";

    public void printDeadlineLacksArgumentsMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_COMMAND_LACKS_ARG);
        System.out.println(MESSAGE_CORRECT_DEADLINE_FORMAT);
        System.out.println(OUTPUT_DIVIDER);
    }

}
