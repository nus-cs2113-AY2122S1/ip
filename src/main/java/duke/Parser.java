package duke;

public class Parser {

    //Default values for tasks
    private static final String DEFAULT_DEADLINE_TIME_CONTENT = "No deadline provided";
    private static final String DEFAULT_EVENT_TIME_CONTENT = "No date provided";

    //@@author okkhoy-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts
    //with minor modifications
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split(" ", 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    //@@author naijie2108-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts
    //with minor modifications
    public static String[] splitDeadlineDescriptionAndDate(String rawDescription) {
        String[] split = rawDescription.trim().split("/by", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split.length == 2 ? split : new String[]{split[0], DEFAULT_DEADLINE_TIME_CONTENT};
    }

    //@@author naijie2108-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts
    //with minor modifications
    public static String[] splitEventDescriptionAndDate(String rawDescription) {
        String[] split = rawDescription.trim().split("/at", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split.length == 2 ? split : new String[]{split[0], DEFAULT_EVENT_TIME_CONTENT};
    }
}
