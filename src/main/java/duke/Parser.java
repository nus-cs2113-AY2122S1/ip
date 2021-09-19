package duke;

/**
 * Parses commands and input from the user to make sense of user commands.
 */
public class Parser {

    //Default values for tasks
    private static final String DEFAULT_DEADLINE_TIME_CONTENT = "No deadline provided";
    private static final String DEFAULT_EVENT_TIME_CONTENT = "No date provided";

    //@@author okkhoy-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts
    //with minor modifications
    /**
     * Splits the command type and command arguments from raw user input.
     * If there are no command arguments in the input, returns blank space as argument instead.
     *
     * @param rawUserInput String representing input from the user.
     * @return A String array of length == 2, with split[0] being the command type, and split[1] being the command argument.
     */
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split(" ", 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    //@@author naijie2108-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts
    //with minor modifications
    /**
     * Splits the description and time in the raw description of a Deadline task.
     *
     * @param rawDescription Raw description of the Deadline task consisting of both description and time of the task.
     * @return A string array of length == 2, with split[0] containing the description, and split[1] containing the task time.
     */
    public static String[] splitDeadlineDescriptionAndTime(String rawDescription) {
        String[] split = rawDescription.trim().split("/by", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split.length == 2 ? split : new String[]{split[0], DEFAULT_DEADLINE_TIME_CONTENT};
    }

    //@@author naijie2108-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts
    //with minor modifications
    /**
     * Splits the description and time in the raw description of an Event task.
     *
     * @param rawDescription Raw description of the Event task consisting of both description and time of the task.
     * @return A string array of length == 2, with split[0] containing the description, and split[1] containing the task time.
     */
    public static String[] splitEventDescriptionAndDate(String rawDescription) {
        String[] split = rawDescription.trim().split("/at", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split.length == 2 ? split : new String[]{split[0], DEFAULT_EVENT_TIME_CONTENT};
    }
}
