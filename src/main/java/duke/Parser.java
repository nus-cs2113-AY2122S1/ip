package duke;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Parses commands and input from the user to make sense of user commands.
 */
public class Parser {

    //@@author naijie2108-reused
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
     * @throws DukeException If deadline format is wrong.
     */
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

    //@@author naijie2108-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts
    //with minor modifications
    /**
     * Splits the description and time in the raw description of an Event task.
     *
     * @param rawDescription Raw description of the Event task consisting of both description and time of the task.
     * @return A string array of length == 2, with split[0] containing the description, and split[1] containing the task time.
     * @throws DukeException If event format is wrong.
     */
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

    //@@author naijie2108-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts
    //with minor modifications

    /**
     * Splits the date and time in the dateTime input of Deadline and Event tasks.
     *
     * @param dateTime Date time input of Deadline and Event tasks.
     * @return A string array of length == 2, with split[0] containing the date and split[1] containing the time.
     */
    public static String[] splitDateAndTime(String dateTime) {
        String[] split = dateTime.trim().split(" ", 2);
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split;
    }

    //@@author naijie2108-reused
    //Reused from https://github.com/nus-cs2113-AY2122S1/contacts
    //with minor modifications
    /**
     * Parses date and time input of Deadline and Event tasks into a <code>LocalDateTime</code> object.
     * @param dateTime Date time input of Deadline and Event tasks.
     * @return Date and Time of represented by dateTime as a <code>LocalDateTime</code> object.
     * @throws DateTimeParseException If the date and time input cannot be parsed.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException{
        LocalDateTime parsedDateTime;
        final String[] timeSplit = splitDateAndTime(dateTime);
        final String date = timeSplit[0];
        final String time = timeSplit[1];
        parsedDateTime = LocalDateTime.parse(date + "T" + time);
        return parsedDateTime;
    }
}
