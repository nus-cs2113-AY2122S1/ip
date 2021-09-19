package duke;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {

    //Default values for tasks
    private static final String DEFAULT_DEADLINE_TIME_CONTENT = "No deadline provided";

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
    public static String[] splitDeadlineDescriptionAndTime(String rawDescription) throws DukeException{
        String[] split = rawDescription.trim().split("/by", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        if (split.length != 2) {
            throw new DukeException(ExceptionMessages.EXCEPTION_WRONG_DEADLINE_FORMAT);
        }
        return split;
    }

    public static String[] splitEventDescriptionAndTime(String rawDescription) throws DukeException{
        String[] split = rawDescription.trim().split("/at", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        if (split.length != 2) {
            throw new DukeException(ExceptionMessages.EXCEPTION_WRONG_EVENT_FORMAT);
        }
        return split;
    }

    public static String[] splitDateAndTime(String dateTime) {
        String[] split = dateTime.trim().split(" ", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split;
    }

    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException{
        LocalDateTime parsedDateTime;
        final String[] timeSplit = splitDateAndTime(dateTime);
        final String date = timeSplit[0];
        final String time = timeSplit[1];
        parsedDateTime = LocalDateTime.parse(date + "T" + time);
        return parsedDateTime;
    }
}
