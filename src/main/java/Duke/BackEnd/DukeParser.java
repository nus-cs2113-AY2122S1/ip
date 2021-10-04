package Duke.BackEnd;

import Duke.Exception.DukeException;
import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static Duke.UI.DukeConstants.EVENT_KEYWORD;
import static Duke.UI.DukeConstants.FORMAT_DATE_TIME_INPUT;
import static Duke.UI.DukeConstants.DEADLINE_KEYWORD;

public class DukeParser {

    /**
     * Method parses the instruction type, which is the first word,
     * from the rest of the instruction from user input
     *
     * @param inWord the user input
     * @return returns the instruction type
     */
    public static String getCommandType(String inWord) {
        String[] instruction = inWord.split("\\s+", 2);
        String instructionType = instruction[0].trim().toLowerCase();
        return instructionType;
    }

    /**
     * Method parses user instruction if instruction is a "Done" type
     *
     * @param inWord "Done" type user instruction
     * @return the parsed user instruction in a string array
     */
    public static String[] parseDoneInstruction (String inWord) {
        String[] commands = inWord.split(" ");
        return commands;
    }

    /**
     * Method parses user instruction if instruction is a "Todo" type
     *
     * @param inWord "Todo" type user instruction
     * @return the parsed user instruction in a string array
     */
    public static String[] parseTodoInstruction (String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        return commands;
    }

    /**
     * Method parses user instruction if instruction is an "Event" type
     *
     * @param inWord "Event" type user instruction
     * @return the parsed user instruction in a string array
     */
    public static String[] parseEventInstruction (String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        return commands;
    }

    /**
     * Method parses the description section of user instruction
     * if instruction is an "Event" type
     *
     * @param commands the parsed user instruction in a string array
     * @return the newly created Event Object
     * @throws DateTimeParseException If the user input's date-time format is invalid
     */
    public static Event parseEventDescription (String[] commands) throws DateTimeParseException{
        try {
            String[] details = commands[1].split(EVENT_KEYWORD, 2);
            String description = details[0].trim();
            String at = details[1].trim();
            LocalDateTime eventAt = parseDateTime(at, FORMAT_DATE_TIME_INPUT);
            return new Event(description, eventAt);
        } catch (DateTimeParseException dtpException) {
            DukeException.dateTimeParseException(dtpException);
            return null;
        }
    }

    /**
     * Method parses user instruction if instruction is a "Deadline" type
     *
     * @param inWord "Deadline" type user instruction
     * @return the parsed user instruction in a string array
     */
    public static String[] parseDeadlineInstruction (String inWord) {
        String[] commands = inWord.split("\\s+", 2);
        return commands;
    }

    /**
     * Method parses the description section of user instruction
     * if instruction is a "Deadline" type
     *
     * @param commands the parsed user instruction in a string array
     * @return the newly created Deadline Object
     * @throws DateTimeParseException If the user input's date-time format is invalid
     */
    public static Deadline parseDeadlineDescription (String[] commands) throws DateTimeParseException {
        try {
            String[] details = commands[1].split(DEADLINE_KEYWORD, 2);
            String description = details[0].trim();
            String by = details[1].trim();
            LocalDateTime deadlineBy = parseDateTime(by, FORMAT_DATE_TIME_INPUT);
            return new Deadline(description, deadlineBy);
        } catch (DateTimeParseException dtpException) {
            DukeException.dateTimeParseException(dtpException);
            return null;
        }
    }

    /**
     * Method parses user instruction if instruction is a "Delete" type
     *
     * @param inWord "Delete" type user instruction
     * @return the parsed user instruction in a string array
     */
    public static String[] parseDeleteInstruction (String inWord) {
        String[] commands = inWord.split(" ");
        return commands;
    }


    /**
     * Method parses user instruction if instruction is a "Find" type
     *
     * @param inWord "Find" type user instruction
     * @return the parsed user instruction in a string array
     * @throws DukeException if the Find instruction is of invalid length
     */
    public static String parseFindInstruction (String inWord) throws DukeException {
        String[] commands = inWord.split("\\s+", 2);
        if (commands.length != 2) {
            throw new DukeException();
        }
        return commands[1].trim();
    }

    /**
     * Method parses user date-time description and converts
     * the string input into LocalDateTime type
     *
     * @param dateTime User date-time description
     * @param dateTimeFormat the format to convert the user date-time to
     * @return the formatted LocalDateTime object
     * @throws DateTimeParseException if the user date-time description is of an invalid format
     */
    public static LocalDateTime parseDateTime (String dateTime, String dateTimeFormat) throws DateTimeParseException{
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimeFormat);
        return LocalDateTime.parse(dateTime, format);
    }

    /**
     * Method converts the LocalDateTime object into a string
     *
     * @param dateTime LocalDateTime object to be converted
     * @param dateTimeFormat the format to convert the LocalDateTime object to
     * @return the formatted date-time string
     */
    public static String convertDateTimeToString (LocalDateTime dateTime, String dateTimeFormat) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimeFormat);
        return dateTime.format(format);
    }
}
